package com.cos.photogramstart.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final EntityManager em;

    @Transactional(readOnly = true)
    public List<SubscribeDto> 구독리스트(Integer principalId, Integer pageUserId) {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.username, u.profileImgUrl, ");
        sb.append("if((SELECT 1 FROM subscribe WHERE fromUserId = ? AND toUserId = u.id) ,1,0)subscribeState, ");
        sb.append("if((? = u.id),1,0) equlUserState ");
        sb.append("FROM user u INNER JOIN subscribe s ");
        sb.append("ON u.id = s.toUserId ");
        sb.append("WHERE s.fromUserId = ?");

        // 쿼리 완성
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, pageUserId);

        // 쿼리 실행(qlrm: dto에 db결과 매핑하기 위함)
        JpaResultMapper result = new JpaResultMapper();
        List<SubscribeDto> subscribeDtos = result.list(query, SubscribeDto.class);

        return subscribeDtos;
    }

    @Transactional
    public void 구독하기(Integer fromUserId, Integer toUserId) {
        try {
            subscribeRepository.mSubscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 구독중입니다");
        }
    }

    @Transactional
    public void 구독취소하기(Integer fromUserId, Integer toUserId) {

        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }

}