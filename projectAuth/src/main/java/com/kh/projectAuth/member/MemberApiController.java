package com.kh.projectAuth.member;

import com.kh.projectAuth.filter.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
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
    private final MyUserDetailsService myUserDetailsService;

    @PostMapping("join")
    public ResponseEntity<Integer> join(@RequestBody MemberDto dto) {
         int result = memberService.join(dto);

         if(result == 1) {
             return ResponseEntity.badRequest().body(0);
         } else {
             return ResponseEntity.ok().body(1);
         }
    }


//    @PostMapping("login")
//    public ResponseEntity<MemberEntity> login(@RequestBody MemberDto dto) {
//        MemberEntity entity = memberService.login(dto);
//
//        if(entity == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entity);
//        } else {
//            return ResponseEntity.ok().body(entity);
//        }
//    }
}
