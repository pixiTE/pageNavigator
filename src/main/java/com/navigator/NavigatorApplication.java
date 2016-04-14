package com.navigator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class NavigatorApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(NavigatorApplication.class, args);

    }
}
