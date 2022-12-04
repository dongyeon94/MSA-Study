package com.coffee.msa.configs;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Slf4j
@Component
public class ConfigTestController {

    @Value("${my-config.take.t1}")
    private String conf1;

    @Value("${my-config.take.t2}")
    private String conf2;

    @PostConstruct
    public void initConfigTest() {
        log.info("======================");
        log.info("conf1 : " + conf1);
        log.info("conf2 : " + conf2);
        log.info("======================");
    }
}
