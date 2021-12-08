package com.crypto.exchange.contentmng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.gd","com.crypto.exchange"})
@EntityScan({"com.gd","com.crypto"})
public class ContentMngApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentMngApplication.class, args);
    }

}
