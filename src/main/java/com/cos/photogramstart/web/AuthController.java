package com.cos.photogramstart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller //1. ioc등록 2. file리턴 컨트롤러 
public class AuthController {
    
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
  
    //di
    private final AuthService authService;

    //public AuthController(AuthService authService){
    // this.authService=authService;
    // }
    
    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    //회원가입 버튼 클릭 -> /auth/signup -> 리턴하면 /auth/signin
     @PostMapping("/auth/signup")
     public String signup(SignupDto signupDto) {

         log.info(signupDto.toString());
         
         //user에다가 singnupdto를 담기
         User user = signupDto.toEntity();
         log.info(user.toString());
    
         User userEntity = authService.회원가입(user);
            System.out.println(userEntity); 
        
         return "auth/signin";
    }
}
