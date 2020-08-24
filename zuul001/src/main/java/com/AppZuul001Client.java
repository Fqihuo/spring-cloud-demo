package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class AppZuul001Client {
    public static void main(String[] args) {
        SpringApplication.run(AppZuul001Client.class);
    }
}
