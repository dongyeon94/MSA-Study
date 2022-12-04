package com.coffee.msa.service;

import com.coffee.msa.domain.OrderEntity;
import com.coffee.msa.domain.OrderProducerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatusExtService {

    private static final String TOPIC = "dymenutopic1";
    private final KafkaTemplate<String, OrderProducerDTO> kafkaTemplate;

    public void sendOrder(String orderId, String menu, String count, String username) {
        OrderProducerDTO orderProducerDTO = new OrderProducerDTO();
        orderProducerDTO.setOrderId(orderId);
        orderProducerDTO.setOrderMenu(menu);
        orderProducerDTO.setCount(count);
        orderProducerDTO.setUsername(username);

        kafkaTemplate.send(TOPIC, orderProducerDTO);
    }

    public String pushOrder(OrderEntity orderEntity, String userName) throws IllegalAccessException {
        String uri = "http://localhost:8080/status/order";

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
