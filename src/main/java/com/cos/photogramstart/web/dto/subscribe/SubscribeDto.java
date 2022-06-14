package com.cos.photogramstart.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {
    private Integer id;
    private String username;
    private String profileImageUrl;
    private Integer subscribeState;
    private Integer equalUserState; // 지금 뜬 애가 로그인한 애랑 동일인지 아닌지 보여주는
}
