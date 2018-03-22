package com.sakamichi46.sakamichiquiz;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface StatsRepository extends ReactiveMongoRepository<Stats, Integer> {
    public Mono<Quiz> findByNo(int no);
}