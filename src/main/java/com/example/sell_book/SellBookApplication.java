package com.example.sell_book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SellBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellBookApplication.class, args);
    }

}
