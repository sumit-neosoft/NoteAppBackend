package com.reactive.repository;

import com.reactive.model.Note;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactor.core.publisher.Flux;

@EnableReactiveMongoRepositories
public interface NoteRepository extends ReactiveMongoRepository<Note, String> {

    Flux<Note> findAllByUserId(String id);
}
