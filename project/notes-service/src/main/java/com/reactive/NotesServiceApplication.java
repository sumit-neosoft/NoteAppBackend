package com.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.ReactiveLoadBalancerAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@EnableEurekaClient
@EnableReactiveFeignClients
@EnableFeignClients
@SpringBootApplication(exclude = ReactiveLoadBalancerAutoConfiguration.class)
public class NotesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotesServiceApplication.class, args);
    }

}
