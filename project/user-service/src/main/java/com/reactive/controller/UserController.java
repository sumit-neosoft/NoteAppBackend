package com.reactive.controller;

import com.reactive.model.User;
import com.reactive.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public Flux<User> getAllItems() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> getTweetById(@PathVariable(value = "id") String userId) {
        return userService.findById(userId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @PostMapping("/")
    public Mono<ResponseEntity<User>> saveUser(@Valid @RequestBody User user) {

        return userService
                .saveUsers(user)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.error(new Exception(
                        "Email Already Exist", e)));

    }

    @PutMapping("/")
    public Mono<ResponseEntity<User>> updateUser(@Valid @RequestBody User user) {
        return userService.findById(user.getId())
                .flatMap(existingUser -> {
                    existingUser.setFirstName(user.getFirstName());
                    existingUser.setLastName(user.getLastName());
                    existingUser.setEmail(user.getEmail());
                    return userService.saveUsers(existingUser);
                })
                .map(updatedUser -> new ResponseEntity<>(updatedUser, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
