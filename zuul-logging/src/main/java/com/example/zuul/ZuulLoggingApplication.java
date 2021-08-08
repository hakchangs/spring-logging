package com.example.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ZuulLoggingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulLoggingApplication.class, args);
    }

}
