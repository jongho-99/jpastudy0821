package com.kh.app09Security.security;

import com.kh.app09Security.member.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class MyUserDetails implements UserDetails {
//    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private final MemberVo vo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of( new SimpleGrantedAuthority("ROLE_" + vo.getUserRole()) );
    }

    @Override
    public String getPassword() {
        return vo.getUserPwd();
    }

    @Override
    public String getUsername() {
        return vo.getUserId();
    }

    public String getUserNick() {
        return vo.getUserNick();
    }

    public String getUserRole() {
        return vo.getUserRole();
    }
}
