package com.kh.app06.member.controller;

import com.kh.app06.member.dto.MemberDto;
import com.kh.app06.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/member")
@RequiredArgsConstructor
public class MemberApiController {

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
