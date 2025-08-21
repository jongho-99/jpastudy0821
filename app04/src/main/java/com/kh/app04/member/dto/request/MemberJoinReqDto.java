package com.kh.app04.member.dto.request;

import lombok.Data;

@Data
public class MemberJoinReqDto {

    private String userId;
    private String userPwd;
    private String userNick;

}
