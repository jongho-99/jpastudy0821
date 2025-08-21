package com.kh.app04.member.controller;

import com.kh.app04.member.dto.request.MemberJoinReqDto;
import com.kh.app04.member.dto.request.MemberLoginReqDto;
import com.kh.app04.member.dto.response.MemberLoginRespDto;
import com.kh.app04.member.service.MemberService;
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

    @PostMapping("join")
    public void join(@RequestBody MemberJoinReqDto reqDto) {

        memberService.join(reqDto);

    }

    @PostMapping("login")
    public MemberLoginRespDto login(@RequestBody MemberLoginReqDto reqDto) {

        return memberService.login(reqDto);

    }
}
