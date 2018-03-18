package com.sakamichi46.sakamichiquiz;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AnswerRepository extends ReactiveMongoRepository<Answer, String> {
    public Mono<Answer> findByNoAndAnswer(Integer no, Integer answer);
}