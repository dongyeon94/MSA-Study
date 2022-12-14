package com.coffee.msa.controller;

import com.coffee.msa.domain.OrderEntity;
import com.coffee.msa.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/menu")
    public String orderMenu(String menu,String count, String email) throws Exception {
        Random rand = new Random();

        OrderEntity orders = new OrderEntity();
        orders.setMenu(menu);
        orders.setCount(count);
        orderService.orderMenu(orders, email);
        return "200";
    }

    @PostMapping("/cancel")
    public String orderCancel() {
        return "200";
    }

    @PostMapping("/change")
    public String orderChange() {
        return "200";
    }

    @PostMapping("/test")
    public String test() {
        URI uri = UriComponentsBuilder
                    .fromUriString("http://localhost:8080")
                    .path("/user/test")
                    .encode()
                    .build()
                    .toUri();

        log.info("requset to " + uri);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.postForEntity(uri, "", String.class);
        log.info("response  " + result.getStatusCode());

        return "200";
    }

}
