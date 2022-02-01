package com.example.spring_prac02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
public class SpringPrac02Application {

    @PostConstruct
    public void started() {
        //timezone UTC 세팅
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        System.out.println("현재시각: " + new Date());
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringPrac02Application.class, args);
    }

}
