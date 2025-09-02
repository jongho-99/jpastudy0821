package com.kh.projectAuth.filter;

import com.kh.projectAuth.member.MemberDto;
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

        MemberEntity find_entity = memberRepository.findUserById(username);

        MemberEntity entity = memberRepository.login(find_entity);
        MemberDto dto = MemberDto.from(entity);
        MyUserDetails myUserDetails = new MyUserDetails(dto);
        return myUserDetails;
    }
}