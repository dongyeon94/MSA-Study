package com.coffee.msa.service;

import com.coffee.msa.domain.UserEntity;
import com.coffee.msa.domain.repository.UserRepository;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String join(UserEntity userEntity) {
        Optional<UserEntity> user = userRepository.findByEmail(userEntity.getEmail());

        if(user.isPresent()) {
            throw new DuplicateRequestException();
        }

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);

        return "200";
    }

    public String login(String email, String password) throws Exception {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("없는 유저입니다."));
        if(!passwordEncoder.matches(user.getPassword(), password)) {
            throw new IllegalAccessException("비밀번호가 틀렸습니다.");
        }
        return "200";
    }

    public String checkMember(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("없는 유저입니다."));
        return "200";
    }

    public String passwordReset(String email, String password) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("없는 유저입니다."));
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return "200";
    }
}
