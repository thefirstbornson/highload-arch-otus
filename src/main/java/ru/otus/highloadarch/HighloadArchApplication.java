package ru.otus.highloadarch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories
public class HighloadArchApplication {

    public static void main(String[] args) {
        SpringApplication.run(HighloadArchApplication.class, args);
    }

}
