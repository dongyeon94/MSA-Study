package com.coffee.msa.controller;

import com.coffee.msa.domain.UserEntity;
import com.coffee.msa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public String joinUser(UserEntity userEntity) {
        userService.join(userEntity);
        return "200";
    }

    @PostMapping("/login")
    public String loginUser(String email, String password) throws Exception {
        userService.login(email, password);
        return "200";
    }

    @PostMapping("/check/{email}")
    public String checkMemberExist(@PathVariable("email")String email) {
        System.out.println("get email : " + email);
        return userService.checkMember(email);
    }

    @PostMapping("/reset/password")
    public String resetPassword(String email, String password) {
        userService.passwordReset(email, password);
        return "200";
    }


    @PostMapping("/test")
    public String test() {
        return "200";
    }
}
