package com.hs.springboot.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication(exclude = {MultipartAutoConfiguration.class}) // 스프링에서 multipartResolver관련 자동 설정 해지,
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
