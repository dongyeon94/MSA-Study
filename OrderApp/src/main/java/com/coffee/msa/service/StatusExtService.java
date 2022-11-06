package com.coffee.msa.service;

import com.coffee.msa.domain.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;

@Slf4j
@Service
public class StatusExtService {

    public String pushOrder(OrderEntity orderEntity, String userName) throws IllegalAccessException {
        String uri = "http://localhost:8093/status/order";

        log.info("push order to" + uri);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("orderId", orderEntity.getOrderId().toString());
        params.add("userName", userName);

        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.postForEntity(uri, req, String.class);

        if(result.getBody().equals("admin")) {
            throw new IllegalAccessException("not allow amdin");
        }

        return "200";
    }
}
