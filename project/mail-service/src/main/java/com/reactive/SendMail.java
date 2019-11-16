package com.reactive;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class SendMail {

    @GetMapping("/createUser")
    private Mono<String> sendUserMail() {
        System.out.println("--------Sending Mail Of User Created------");
        return Mono.just(String.format("Mail Sent!!"));
    }

    @GetMapping("/createNote")
    private Mono<String> sendNoteMail() {
        System.out.println("--------Sending Mail of Note------");
        return Mono.just(String.format("Mail Sent!!"));
    }
}
