package com.kh.projectAuth.member;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberDto {

    private Long no;
    private String userId;
    private String userPwd;
    private String userNick;

    private String role;

    private String companyToken;
    private Long departmentNo;

    private String delYn;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
