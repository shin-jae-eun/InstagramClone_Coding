package com.cos.photogramstart.domain.image;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Image {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호증가전략: 데이터베이스를 따라가겠다.
    @Id
    private Integer id;

    private String caption;
    private String postImageUrl;

    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;

    // 이미지 좋아요

    // 댓글

    private LocalDateTime createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    // 오브젝트를 콘솔에 출력 시, 문제 될 수 있어 user부분을 출력되지 않도록 만듦.
    // @Override
    // public String toString() {
    // return "Image [caption=" + caption + ", createDate=" + createDate + ", id=" +
    // id + ", postImageUrl="
    // + postImageUrl + "]";
    // }
}
