package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor // 빈 생성자
@AllArgsConstructor // 전체 생성자
@Data // getter,setter
@Entity // 디비에 테이블을 생성시켜준다.
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호증가전략: 데이터베이스를 따라가겠다.
    @Id
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String website; // 웹사이트
    private String bio; // 자기소개

    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;

    private String profileImgUrl; // 사진
    private String role; // 권한

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({ "user" })
    private List<Image> images;

    private LocalDateTime createDate;

    @PrePersist // 디비에 insert되기 전에 실행된다.
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "User [bio=" + bio + ", createDate=" + createDate + ", email=" + email + ", gender=" + gender + ", id="
                + id + ", name=" + name + ", password=" + password + ", phone=" + phone
                + ", profileImgUrl=" + profileImgUrl + ", role=" + role + ", username=" + username + ", website="
                + website + "]";
    }

}
