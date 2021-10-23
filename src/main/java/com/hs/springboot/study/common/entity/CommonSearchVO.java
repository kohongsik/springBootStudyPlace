package com.hs.springboot.study.common.entity;

import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

public class CommonSearchVO {
    private String createUserId;
    private String createUserName;
    private LocalDateTime createDate;
    private String updateUserId;
    private String updateUserName;
    private LocalDateTime updateDate;
}
