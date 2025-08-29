package com.kh.app09Security.member;

import lombok.Data;

@Data
public class MemberVo {

    private String userId;
    private String userPwd;
    private String userNick;
    private String userRole;
}
