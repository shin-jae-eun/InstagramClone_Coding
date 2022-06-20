package com.cos.photogramstart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.likes.LikesRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likesRepository;

    @Transactional
    public void 좋아요(Integer imageId, Integer principalId) {
        likesRepository.mLikes(imageId, principalId);
    }

    @Transactional
    public void 좋아요취소(Integer imageId, Integer principalId) {
        likesRepository.mUnLikes(imageId, principalId);
    }

}
