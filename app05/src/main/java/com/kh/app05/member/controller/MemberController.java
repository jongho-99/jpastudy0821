package com.kh.app05.member.controller;


import com.kh.app05.member.dto.MemberDto;
import com.kh.app05.member.entity.MemberEntity;
import com.kh.app05.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("join")
    public void join(@RequestBody MemberDto dto) {
        memberService.join(dto);
    }

    @PostMapping("login")
    public MemberDto login(@RequestBody MemberDto dto) {
        return memberService.login(dto);
    }

    @GetMapping
    public List<MemberDto> list() {
        return memberService.list();
    }

}
