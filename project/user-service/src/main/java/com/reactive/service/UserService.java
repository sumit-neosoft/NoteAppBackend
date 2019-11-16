package com.reactive.service;

import com.reactive.model.User;
import com.reactive.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService  {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Flux<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public Mono<User> saveUsers(User user) {
        return this.userRepository.save(user);
    }

    public Mono<User> findById(String id) {
        return userRepository.findById(id);
    }

}
