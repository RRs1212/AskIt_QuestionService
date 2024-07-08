package com.AskIt.QuestionService.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Builder
public class CreateQuestionDto {

    private Long userId;

    private String question;

    private List<String> topics;



}

