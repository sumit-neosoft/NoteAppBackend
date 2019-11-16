package com.reactive.feignClients;


import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "mail-service")
public interface MailService {

    @GetMapping("/createNote")
    Mono<String> createNote();


}
