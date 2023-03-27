package com.qlsv5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication

public class Qlsv5Application {

    public static void main(String[] args) {
        SpringApplication.run(Qlsv5Application.class, args);
    }

}
