package com.hdsong.activiti.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class, org.activiti.spring.boot.SecurityAutoConfiguration.class})
public class AcitvitiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcitvitiDemoApplication.class, args);
    }

}
