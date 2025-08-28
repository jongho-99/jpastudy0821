package com.kh.projectAuth.filter;

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

@RequiredArgsConstructor
public class MyLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;


    //로그인 시도
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("MyLoginFilter.attemptAuthentication");

        Authentication authToken = new UsernamePasswordAuthenticationToken("user01", "1234", null);

        return authenticationManager.authenticate(authToken);

    }

    //로그인 성공
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {

        System.out.println("MyLoginFilter.successfulAuthentication");

        MyUserDetails userDetails = (MyUserDetails) authResult.getPrincipal();

        String userId = userDetails.getUsername();
        String userNick = userDetails.getUserNick();
        String userRole = userDetails.getUserRole();

        String jwt = myJwtUtil.createJWT(userId, userNick, userRole);
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
