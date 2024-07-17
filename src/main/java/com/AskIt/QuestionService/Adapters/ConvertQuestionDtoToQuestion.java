package com.AskIt.QuestionService.Adapters;

import com.AskIt.EntityService.Models.Question;
import com.AskIt.EntityService.Models.Topic;
import com.AskIt.EntityService.Models.User;
import com.AskIt.QuestionService.Dto.CreateQuestionDto;

import com.AskIt.QuestionService.Repositories.TopicRepository;
import com.AskIt.QuestionService.Repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class ConvertQuestionDtoToQuestion implements ConvertToQuestion{


    private UserRepository userRepository;

    private TopicRepository topicRepository;

    private Topic topic;

    List<Topic>questionTopic=new ArrayList<>();

    private Question question;


    public ConvertQuestionDtoToQuestion(UserRepository userRepository,TopicRepository topicRepository){
        this.userRepository=userRepository;
        this.topicRepository=topicRepository;
    }
    @Override
    public Question ConvertToQuestion(CreateQuestionDto createQuestionDto) {
        Optional<User> user=userRepository.findById(createQuestionDto.getUserId());


        if(user.isPresent()){

            for (String i : createQuestionDto.getTopics()) {

                Optional<Topic>topic=topicRepository.findByName(i);

                if(topic.isPresent()){
                    questionTopic.add(topic.get());
                }else{
                    questionTopic.add(topicRepository.save(Topic.builder().name(i).build()));
                }

            }

            System.out.println(questionTopic.size());

            question = Question.builder().user(user.get()).body(createQuestionDto.getQuestion()).topics(questionTopic).build();



        }

        return question;
    }
}
