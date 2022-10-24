package com.coffee.msa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigTestController {

    @Value("${my-config.take.t1}")
    private String configStr;

    @GetMapping("/test")
    public String test() {
        return configStr;
    }
}
