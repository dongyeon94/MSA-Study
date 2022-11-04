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
    private final MemberExtService service;

    public String orderMenu(OrderEntity orderEntity) throws Exception{
        String result = service.checkMember("test");
        if(!result.equals("200")) {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }

        orderEntity.setOrderTime(LocalDateTime.now());
        orderEntity.setCount(orderEntity.getCount());
        orderEntity.setMenu(orderEntity.getMenu());
        orderRepository.save(orderEntity);
        return "200";
    }

    public String orderChange(OrderEntity order) {
        OrderEntity orderEntity = orderRepository.findByUuid(order.getUuid()).orElseThrow(NullPointerException::new);
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
