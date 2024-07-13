package com.AskIt.QuestionService.Dto;


import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class NewQuestionCreatedDto {

    private Long questionId;

    private String questionBody;

    private Long userId;

    private String userName;

    private List<String>topics;
}
