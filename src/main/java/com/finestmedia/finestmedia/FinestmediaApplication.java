package com.finestmedia.finestmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FinestmediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinestmediaApplication.class, args);
    }

}
