package com.reactive.repository;

import com.reactive.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;


@EnableReactiveMongoRepositories
public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
