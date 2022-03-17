package com.example.quarzdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QuarzDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(QuarzDemoApplication.class, args);
    }


}
