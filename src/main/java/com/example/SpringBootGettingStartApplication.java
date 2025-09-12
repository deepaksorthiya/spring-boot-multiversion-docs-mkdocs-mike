package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
// <main>
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class SpringBootGettingStartApplication {

    private final static Logger LOGGER = LoggerFactory.getLogger(SpringBootGettingStartApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGettingStartApplication.class, args);
    }

}
// </main>
