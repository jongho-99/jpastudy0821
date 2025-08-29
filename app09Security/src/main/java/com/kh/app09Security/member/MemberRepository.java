package com.kh.app09Security.member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public MemberVo login(String userId) {

//      em.createQuery()
        MemberVo vo = new MemberVo();
        vo.setUserId("user01");
        vo.setUserPwd(bCryptPasswordEncoder.encode("1234"));
        vo.setUserNick("nick01");
        vo.setUserRole("ADMIN");
        return vo;

    }
}
