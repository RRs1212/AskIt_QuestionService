package com.AskIt.QuestionService.Adapters;

import com.AskIt.EntityService.Models.Question;
import com.AskIt.QuestionService.Dto.CreateQuestionDto;


public interface ConvertToQuestion {

    Question ConvertToQuestion(CreateQuestionDto createQuestionDto);
}
