package com.coffee.msa.controller;

import com.coffee.msa.domain.MenuStatus;
import com.coffee.msa.domain.OrderStatus;
import com.coffee.msa.service.MenuStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/status")
public class MenuStatusController {

    private final MenuStatusService menuStatusService;

    @PostMapping("/order")
    public String newOrder(String orderId, String userName) {
        log.info("[Controller]  orderid : " + orderId);
        log.info("[Controller]  userName : " + userName);
        return menuStatusService.registorMenu(orderId, userName);
    }

    @PostMapping("/change")
    public String orderStatusChange(String orderId, OrderStatus status) {
        log.info("[Controller]  orderid : " + orderId);
        log.info("[Controller]  status : " + status);
        menuStatusService.statusChange(orderId, status);
        return "200";
    }

    @PostMapping("/test")
    public String test() {
        return "200";
    }
}
