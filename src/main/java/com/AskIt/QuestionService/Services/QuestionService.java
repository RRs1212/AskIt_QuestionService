package com.AskIt.QuestionService.Services;


import com.AskIt.EntityService.Models.Question;
import com.AskIt.EntityService.Models.Topic;
import com.AskIt.EntityService.Models.User;

import java.util.Optional;

public interface QuestionService {

    Question CreateQuestion(Question q);

    Optional<Question> GetQuestionByQuestionID(Long id);

    Question GetQuestionByUser(User user);


    Question GetQuestionBtTextandTopic( Topic topic);


}
