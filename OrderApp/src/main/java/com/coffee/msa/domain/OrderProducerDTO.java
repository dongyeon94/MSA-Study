package com.coffee.msa.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderProducerDTO {
    private String orderId;
    private String orderMenu;
    private String count;
    private String username;
}
