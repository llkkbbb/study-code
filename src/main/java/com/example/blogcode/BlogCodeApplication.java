package com.example.blogcode;

import lombok.experimental.PackagePrivate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class BlogCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogCodeApplication.class, args);

    }

}
