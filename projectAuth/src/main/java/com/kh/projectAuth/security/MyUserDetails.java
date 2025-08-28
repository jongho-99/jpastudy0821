package com.kh.projectAuth.security;

import com.kh.projectAuth.member.MemberDto;
import com.kh.projectAuth.member.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class MyUserDetails implements UserDetails {

    private final MemberEntity entity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return entity.getUserPwd();
    }

    @Override
    public String getUsername() {
        return entity.getUserId();
    }

    public String getUserNick() {
        return entity.getUserNick();
    }

    public String getUserRole() {
        return entity.getRole();
    }
}
