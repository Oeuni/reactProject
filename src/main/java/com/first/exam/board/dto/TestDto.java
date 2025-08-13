package com.first.exam.board.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestDto {   //사용자가 입력한 답
    private String answer;
}
