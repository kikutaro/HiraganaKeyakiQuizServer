package com.sakamichi46.sakamichiquiz;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    @Id
    private int no;
    private Member name;
    private String question;
    private String image;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
}