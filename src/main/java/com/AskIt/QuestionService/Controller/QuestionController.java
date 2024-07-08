package com.AskIt.QuestionService.Controller;

import com.AskIt.EntityService.Models.Question;
import com.AskIt.EntityService.Models.User;
import com.AskIt.QuestionService.Adapters.ConvertToQuestion;
import com.AskIt.QuestionService.Dto.CreateQuestionDto;

import com.AskIt.QuestionService.Repositories.UserRepository;
import com.AskIt.QuestionService.Services.QuestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/question")
public class QuestionController {


    private final QuestionService questionService;

    private final UserRepository userRepository;

    private final ConvertToQuestion convertToQuestion;

    public QuestionController(QuestionService questionService, ConvertToQuestion convertToQuestion,
                              UserRepository userRepository){
        this.questionService=questionService;
        this.convertToQuestion = convertToQuestion;
        this.userRepository=userRepository;
    }


    @PostMapping("/new")
    public void createQuestion(@RequestBody CreateQuestionDto createQuestionDto){


        User user= User.builder().name("Ravi").email("raja@345").password("123456").build();
        userRepository.save(user);

        System.out.println(createQuestionDto.getQuestion());
        System.out.println(createQuestionDto.getUserId());
        System.out.println(createQuestionDto.getTopics());
        Question q =convertToQuestion.ConvertToQuestion(createQuestionDto);

        questionService.CreateQuestion(q);

    }
}
