package com.gd.contentmng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.gd")
public class ContentMngApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentMngApplication.class, args);
    }

}
