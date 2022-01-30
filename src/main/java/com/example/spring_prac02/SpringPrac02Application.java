package com.example.spring_prac02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpringPrac02Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringPrac02Application.class, args);
    }

}
