package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service //1.ioc //2.transaction관리
public class AuthService {
    
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional 
    public User 회원가입(User user) {
        //회원가입 진행 위해서는 repository필요
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword); //암호화 - 해쉬화
        user.setPassword(encPassword); //user패스워드에 해쉬화된 패스워드 넣어준다.
        user.setRole("ROLE_USER"); //관리자는 ROLE_ADMIN

        User userEntity = userRepository.save(user);
        return userEntity;
    }
}
