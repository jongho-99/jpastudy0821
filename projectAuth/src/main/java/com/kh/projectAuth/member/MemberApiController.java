package com.kh.projectAuth.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/member")
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("join")
    public ResponseEntity<Integer> join(@RequestBody MemberDto dto) {
        return memberService.join(dto);

    }
}
