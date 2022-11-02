package com.coffee.msa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue
    private Long        id;

    private String      email;
    private String      username;
    private String      password;
    private String      phonenumber;
}
