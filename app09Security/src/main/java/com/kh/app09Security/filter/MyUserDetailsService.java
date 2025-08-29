package com.kh.app09Security.filter;

import com.kh.app09Security.member.MemberRepository;
import com.kh.app09Security.member.MemberVo;
import com.kh.app09Security.security.MyUserDetails;
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

        MemberVo vo = memberRepository.login(username);
        MyUserDetails myUserDetails = new MyUserDetails(vo);
        return myUserDetails;
    }
}
