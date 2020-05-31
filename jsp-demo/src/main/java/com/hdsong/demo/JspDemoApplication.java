package com.hdsong.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@SpringBootApplication
@RestController
public class JspDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JspDemoApplication.class, args);
    }

    @PostMapping("/world")
    public String world(@RequestParam(value = "userId") String id){
        return id;
    }
}
