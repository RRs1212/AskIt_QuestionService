package com.AskIt.QuestionService.Controller;

import com.AskIt.EntityService.Models.Question;
import com.AskIt.EntityService.Models.Topic;
import com.AskIt.EntityService.Models.User;
import com.AskIt.QuestionService.Adapters.ConvertToQuestion;
import com.AskIt.QuestionService.Dto.CreateQuestionDto;

import com.AskIt.QuestionService.Dto.NewQuestionCreatedDto;
import com.AskIt.QuestionService.Dto.QuestionDto;
import com.AskIt.QuestionService.Repositories.TopicRepository;
import com.AskIt.QuestionService.Repositories.UserRepository;
import com.AskIt.QuestionService.Services.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/question")
public class QuestionController {


    private final QuestionService questionService;

    private final TopicRepository topicRepository;

    private  final UserRepository userRepository;
    private NewQuestionCreatedDto newQuestionCreatedDto;



    private final ConvertToQuestion convertToQuestion;

    public QuestionController(QuestionService questionService, ConvertToQuestion convertToQuestion,
                              TopicRepository topicRepository, UserRepository userRepository){
        this.questionService=questionService;
        this.convertToQuestion = convertToQuestion;
        this.topicRepository=topicRepository;

        this.userRepository = userRepository;
    }


    @PostMapping("/new")
    public ResponseEntity<?> createQuestion(@RequestBody CreateQuestionDto createQuestionDto){


        Question q =convertToQuestion.ConvertToQuestion(createQuestionDto);

        Question question=questionService.CreateQuestion(q);

        newQuestionCreatedDto=NewQuestionCreatedDto.builder().questionId(question.getId())
                .questionBody(question.getBody())
                .userId(question.getUser().getId())
                .userName(question.getUser().getName()).build();



        return new ResponseEntity<>(newQuestionCreatedDto, HttpStatus.CREATED);

    }

    @GetMapping("/ByTopic/{topicName}")
    public ResponseEntity<?> GetQuestionByTopic(@PathVariable String topicName){

        Optional<Topic> topic=topicRepository.findByName(topicName);

        System.out.println(topic.isPresent());



        Topic t=topic.get();
        System.out.println(t.getName());

        List<Question> questionList=questionService.GetQuestionByTopic(t);

        if(!questionList.isEmpty()){
            return new ResponseEntity<>(questionList,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>("no questions found",HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/ByUserId/{userId}")
    public ResponseEntity<?> GetQuestionsByUserId(@PathVariable Long userId){


        Optional<User> user=userRepository.findById(userId);

        User fromUser=user.get();

        questionService.GetQuestionByUser(fromUser);

        return null;

    }

    @GetMapping("/{questionId}")
    public ResponseEntity<?>GetQuestionByQuestionId(@PathVariable Long questionId){

        Optional<Question> question=questionService.GetQuestionByQuestionID(questionId);

        if(question.isPresent()){
            Question q=question.get();
            QuestionDto responseQuestion=QuestionDto.builder().questionId(q.getId()).questionBody(q.getBody()).userId(q.getUser().getId())
                    .userName(q.getUser().getName()).build();
            return new ResponseEntity<>(responseQuestion,HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }





}
