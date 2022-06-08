package com.cos.photogramstart.web.dto.user;

import javax.validation.constraints.NotBlank;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDto {
    
    @NotBlank
    private String name; //필수

    @NotBlank
    private String password; //필수
    private String website;  //선택
    private String bio;//선택
    private String phone; //선택
    private String gender; //선택

        public User toEntity() {
            return User.builder()
                .name(name)
                    .password(password)
                    .website(website)
                    .bio(bio)
                    .phone(phone)
        .gender(gender)
                .build();
    }
}
