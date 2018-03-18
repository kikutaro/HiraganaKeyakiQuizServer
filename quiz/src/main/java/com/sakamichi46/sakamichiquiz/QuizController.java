package com.sakamichi46.sakamichiquiz;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController("/quiz")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @GetMapping("/quiz/all")
    public Flux<Quiz> getQuiz() {
        return quizRepository.findAll();
    }

    @GetMapping
    public Quiz getQuiz(@RequestParam(name="no") int no) {
        Mono<Quiz> quiz = quizRepository.findByNo(no);
        return quiz.block();
    }

    @GetMapping("quiz/random")
    public Quiz getRandomQuiz() {
        long count = quizRepository.count().block();
        int no = ThreadLocalRandom.current().nextInt(1, (int) (count + 1));
        return getQuiz(no);
    }

    @GetMapping("/quiz/answer")
    public Answer getAnswer(@RequestParam(name="no") int no, @RequestParam(name="answer") int ans) {
        Mono<Answer> answer = answerRepository.findByNoAndAnswer(no, ans);
        return answer.block();
    }
}