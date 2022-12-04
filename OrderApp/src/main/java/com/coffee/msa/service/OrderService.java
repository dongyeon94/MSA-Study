package com.coffee.msa.service;

import com.coffee.msa.domain.OrderEntity;
import com.coffee.msa.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberExtService memberExtService;
    private final StatusExtService statusExtService;

    public String orderMenu(OrderEntity orderEntity, String email) throws Exception{
        String userName = memberExtService.checkMember(email);
        if(userName.equals("admin")) {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }

        orderEntity.setOrderTime(LocalDateTime.now());

        orderRepository.save(orderEntity);
//        statusExtService.pushOrder(orderEntity, userName);
        statusExtService.sendOrder(String.valueOf(orderEntity.getOrderId()), orderEntity.getMenu(), orderEntity.getCount(), userName);
        return "200";
    }

    public String orderChange(OrderEntity order) {
        OrderEntity orderEntity = orderRepository.findByOrderId(order.getOrderId()).orElseThrow(NullPointerException::new);
        orderEntity.setMenu(order.getMenu());
        orderEntity.setOrderTime(LocalDateTime.now());
        return "200";
    }

    public String orderCancel(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(NullPointerException::new);
        orderRepository.deleteById(orderId);
        return "200";
    }
}
