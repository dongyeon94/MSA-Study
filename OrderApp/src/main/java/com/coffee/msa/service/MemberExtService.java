package com.coffee.msa.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class MemberExtService {

    public String checkMember(String email) {
        URI uri = UriComponentsBuilder
            .fromUriString("http://localhost:8091")
            .path("/user/check/{email}")
            .encode()
            .build()
            .expand(email)
            .toUri();

        System.out.println(uri.toString());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.postForEntity(uri, "", String.class);

        System.out.println(" ::  " + result.getStatusCode());
        System.out.println(" ::  " + result.getBody());


        if(!result.getBody().equals("200")) {
            return "400";
        }

        return "200";
    }

}
