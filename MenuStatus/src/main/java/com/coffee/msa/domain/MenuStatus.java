package com.coffee.msa.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
public class MenuStatus {

    @Id
    @GeneratedValue
    private Long            id;

    private String          orderId;
    private String          menu;
    private String          orderCount;

    @Enumerated(EnumType.STRING)
    private OrderStatus     status;

    private String          username;
}
