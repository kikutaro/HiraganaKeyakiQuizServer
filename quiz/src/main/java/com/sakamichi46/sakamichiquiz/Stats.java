package com.sakamichi46.sakamichiquiz;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Stats {
    @Id
    private int no;
    private int totalCount;
    private int correctCount;

    public Stats() {
        this.totalCount = 0;
        this.correctCount = 0;
    }

    public Stats(int no) {
        this();
        this.no = no;
    }

    public void countUpTotal() {
        totalCount++;
    }

    public void countUpCorrect() {
        correctCount++;
    }
}