package com.marcia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ApplicationStarter {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationStarter.class, args);
    }
}
