package com.sakamichi46.sakamichiquiz;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Id
    private String _id;
    private int no;
    private int answer;
    private boolean correct;
    private String desc;
}