package com.AskIt.QuestionService.Services;


import com.AskIt.EntityService.Models.Question;
import com.AskIt.QuestionService.Repositories.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService{


    private QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository){
        this.questionRepository=questionRepository;
    }

    @Override
    public void CreateQuestion(Question q) {


        questionRepository.save(q);

    }
}
