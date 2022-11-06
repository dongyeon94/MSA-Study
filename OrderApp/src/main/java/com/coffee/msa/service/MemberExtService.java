package com.coffee.msa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
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

        log.info("check user " + uri);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.postForEntity(uri, "", String.class);

        if(result.getBody().equals("admin")) {
            throw new IllegalArgumentException("유저가 없습니다.");
        }

        return result.getBody();
    }

}
