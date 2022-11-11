package com.example.root;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.ArrayList;

@Component
public class TestProcess {

    private final int LOOP_COUNT = 100;

    @PostConstruct
    public void orderTest() {
        ArrayList<Long> times = new ArrayList<Long>();
        long total = 0;

        for(int i=0; i < LOOP_COUNT; i++) {
            long startTime = System.currentTimeMillis();
            URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/order/menu")
                .encode()
                .build()
                .toUri();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
            params.add("email", "test");

            HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> result = restTemplate.postForEntity(uri, req, String.class);

            long endTime = System.currentTimeMillis();
            long searchTime = endTime - startTime;
            System.out.println("[" + (i+1) + "]  : " +searchTime);
            times.add(searchTime);
            total += searchTime;
        }

        System.out.println(" avg : "+  total / LOOP_COUNT);

    }
}
