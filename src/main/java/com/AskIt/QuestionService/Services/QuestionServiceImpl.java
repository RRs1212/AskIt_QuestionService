package com.AskIt.QuestionService.Services;


import com.AskIt.EntityService.Models.Question;
import com.AskIt.EntityService.Models.Topic;
import com.AskIt.EntityService.Models.User;
import com.AskIt.QuestionService.Repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService{


    private QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository){

        this.questionRepository=questionRepository;
    }

    @Override
    public Question  CreateQuestion(Question q) {

         return  questionRepository.save(q);

    }

    @Override
    public Optional<Question> GetQuestionByQuestionID(Long id) {

        Optional<Question> question =questionRepository.findById(id);
        return question;


    }

    @Override
    public Question GetQuestionByUser(User user) {

        Optional<List<Question>> questionByUser=questionRepository.findByUser(user);


        for(Question q:questionByUser.get()){
            System.out.println(q.getBody());
        }

        return null;



    }

    @Override
    public List<Question> GetQuestionByTopic(Topic topic) {

//        Optional<Question> QuestionSByTopic =questionRepository.findByTopic(topic);
//
//        System.out.println(QuestionSByTopic.get().getBody());

        Optional<List<Question>> questionByTopic=questionRepository.findQuestionByTopic(topic);
        return questionByTopic.get();


    }
}
