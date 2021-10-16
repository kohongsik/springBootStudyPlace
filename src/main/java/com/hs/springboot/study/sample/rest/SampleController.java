package com.hs.springboot.study.sample.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/sample")
public class SampleController {
    @GetMapping(value = "/print-hello-world")
    public void printHelloWorld () {
        System.out.println("HELLO WORLD");
    }
}
