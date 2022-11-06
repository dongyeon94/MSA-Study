package com.coffee.msa.service;

import com.coffee.msa.domain.MenuStatus;
import com.coffee.msa.domain.OrderStatus;
import com.coffee.msa.domain.repository.MenuStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuStatusService {

    private final MenuStatusRepository menuStatusRepository;

    public String registorMenu(String orderId, String userName) {
        MenuStatus menuStatus = new MenuStatus();
        menuStatus.setStatus(OrderStatus.STAY);
        menuStatus.setOrderId(orderId);
        menuStatus.setUsername(userName);
        menuStatusRepository.save(menuStatus);

        log.info("order by : " + userName);
        log.info("order UUID : " + menuStatus.getOrderId());

        return "200";
    }

    @Transactional
    public String statusChange(String orderID, OrderStatus status) {
        MenuStatus menuStatus = menuStatusRepository.findByOrderId(orderID).orElseThrow(() -> new IllegalArgumentException("주문번호가 틀립니다."));
        menuStatus.setStatus(status);
        log.info(orderID + " change Status : " + status);
        return "200";
    }
}
