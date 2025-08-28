package com.kh.projectAuth.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public ResponseEntity<Integer> join(MemberDto dto) implements UserDetailsService{

        MemberEntity entity = new MemberEntity();


    }
}
