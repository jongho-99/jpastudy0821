package com.kh.app10.member.dto;

import com.kh.app10.member.entity.MemberEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberReqDto {

    private Long no;

    private String userId;
    private String userPwd;
    private String userNick;

}
