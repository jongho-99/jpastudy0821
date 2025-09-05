package com.kh.app10.member.controller;

import com.kh.app10.member.dto.MemberDto;
import com.kh.app10.member.dto.MemberReqDto;
import com.kh.app10.member.dto.MemberRespDto;
import com.kh.app10.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/member")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/join")
    public void join(@RequestBody MemberReqDto dto) {

        memberService.join(dto);
    }

    @PostMapping("/login")
    public MemberRespDto login(@RequestBody MemberReqDto dto, HttpSession ss) {

        MemberRespDto loginMember =  memberService.login(dto);
        ss.setAttribute("loginMember", loginMember);

        return loginMember;

    }
}
