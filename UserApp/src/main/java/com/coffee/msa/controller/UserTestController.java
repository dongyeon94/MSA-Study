package com.coffee.msa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserTestController {

    @GetMapping("/user/info")
    public String test() {
        System.out.println("#####################################");

        System.out.println("asdfasdfasdfasdfasdf");

        System.out.println("#####################################");
        return "200";
    }
}

