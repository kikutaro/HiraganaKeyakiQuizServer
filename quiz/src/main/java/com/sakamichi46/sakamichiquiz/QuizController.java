package com.sakamichi46.sakamichiquiz;

import java.util.concurrent.ThreadLocalRandom;

import javax.naming.ldap.StartTlsRequest;

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

    @Autowired
    private StatsRepository statsRepository;

    @GetMapping("/quiz/all")
    public Flux<Quiz> getQuiz() {
        return quizRepository.findAll();
    }

    @GetMapping
    public Quiz getQuiz(@RequestParam(name="no") int no) {
        Mono<Quiz> quiz = quizRepository.findByNo(no);
        return quiz.block();
    }

    @GetMapping("quiz/member")
    public Flux<Quiz> getMemberQuiz(@RequestParam(name="name") String name) {
        Flux<Quiz> quiz = quizRepository.findByName(name);
        return quiz;
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
        Mono<Stats> stats = statsRepository.findById(no);

        Stats stat = stats.block();
        if(stat == null) {
            stat = new Stats(no);
        }
        if(answer.block().isCorrect()) {
            stat.countUpCorrect();
        }
        stat.countUpTotal();
        statsRepository.save(stat).block();

        return answer.block();
    }

    @GetMapping("/quiz/stats")
    public Mono<Stats> getAnswerStats(@RequestParam(name="no") int no) {
        return statsRepository.findById(no);
    }
}