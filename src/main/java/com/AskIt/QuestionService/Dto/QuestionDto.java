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
public class QuestionDto {
    private Long questionId;

    private String questionBody;

    private Long userId;

    private String userName;


}
