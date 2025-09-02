package com.kh.projectAuth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.projectAuth.member.MemberDto;
import com.kh.projectAuth.security.MyJwtUtil;
import com.kh.projectAuth.security.MyUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class MyLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final MyJwtUtil myJwtUtil;


    //로그인 시도
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        try{
            System.out.println("MyLoginFilter.attemptAuthentication");

            ObjectMapper om = new ObjectMapper();
            MemberDto dto = om.readValue(request.getInputStream(), MemberDto.class);

            Authentication authToken = new UsernamePasswordAuthenticationToken(dto.getUserId(), dto.getUserPwd());

            return authenticationManager.authenticate(authToken);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    //로그인 성공
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {

        System.out.println("MyLoginFilter.successfulAuthentication");

        MyUserDetails userDetails = (MyUserDetails) authResult.getPrincipal();

        String userId = userDetails.getUsername();
        String userNick = userDetails.getUserNick();
        String userRoleName = userDetails.getUserRoleName();
        String userDepartmentName = userDetails.getUserDepartmentName();

        LocalDateTime userCreatedAt = userDetails.getUserCreatedAt();

        String jwt = myJwtUtil.createJwt(userId, userNick, userRoleName, userDepartmentName, userCreatedAt);
        response.addHeader("Authorization", "Bearer " + jwt);

    }
        //로그인 실패
        @Override
        protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException
        failed) throws IOException {
            System.out.println("MyLoginFilter.unsuccessfulAuthentication");
            response.setStatus(HttpServletResponse.SC_CONFLICT); // 409
            response.getWriter().write("Login failed"); // 메시지도 직접 작성 가능
        }




}
