package com.hs.springboot.study.common.entity;

import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonVO {
    private String createUserId;
    private String createUserName;
    private LocalDateTime createDate;
    private String updateUserId;
    private String updateUserName;
    private LocalDateTime updateDate;
    private HashMap<String, Object> currentUser;
    private void setCurrentUserInfo(String userId, String timezone) {
        // TODO : 세션 정보 가져오기
        currentUser.put("userId", userId);
        currentUser.put("timezone", timezone);
    }
}
