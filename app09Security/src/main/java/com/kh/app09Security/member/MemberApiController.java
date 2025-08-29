package com.kh.app09Security.member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MemberApiController {

    @GetMapping("home")
    public void home() {
        System.out.println("MemberApiController.home");
    }
    
    @PostMapping("login")
    public void login(@RequestBody MemberVo vo) {
        System.out.println("뭐 제출누름 ㅇㅇ");
    }

}
