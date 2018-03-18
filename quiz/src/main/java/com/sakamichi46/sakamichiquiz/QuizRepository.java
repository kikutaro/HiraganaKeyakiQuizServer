package com.sakamichi46.sakamichiquiz;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Repository
public interface QuizRepository extends ReactiveMongoRepository<Quiz, Integer> {
    public Mono<Quiz> findByNo(int no);
    public Flux<Quiz> findByName(String name);
}