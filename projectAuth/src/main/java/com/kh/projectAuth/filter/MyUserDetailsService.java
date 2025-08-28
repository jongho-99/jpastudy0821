package com.kh.projectAuth.filter;

import com.kh.projectAuth.member.MemberEntity;
import com.kh.projectAuth.member.MemberRepository;
import com.kh.projectAuth.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity entity = memberRepository.login(username);
        MyUserDetails myUserDetails = new MyUserDetails(entity);
        return myUserDetails;
    }
}
