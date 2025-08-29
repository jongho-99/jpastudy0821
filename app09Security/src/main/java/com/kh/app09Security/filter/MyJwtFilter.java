package com.kh.app09Security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.app09Security.member.MemberVo;
import com.kh.app09Security.security.MyJwtUtil;
import com.kh.app09Security.security.MyUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

//요청 시 딱 한번만 지나는 필터
@RequiredArgsConstructor
public class MyJwtFilter extends OncePerRequestFilter {

    private final MyJwtUtil myJwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //신분증 검사 -> 인증 || 인가
        MemberVo vo = new MemberVo();

        String authorization = request.getHeader("Authorization");

        //토큰 있냐 없냐 검증 (아예 처음 사이트 방문시에는 쿠키가 없으므로 없을때도 filterChain.doFilter 진행)
        if(authorization == null || !authorization.startsWith("Bearer ")) {
            System.out.println("토큰이없음 ㅠㅠ");
            filterChain.doFilter(request,response);
            return;
        }



        String accessToken = authorization.split(" ")[1];

        if(myJwtUtil.isExpired(accessToken)) {
            System.out.println("토큰만료 ㅠㅜㅠㅠㅜ");
            filterChain.doFilter(request,response);
            return;
        }
        String userId = myJwtUtil.getUserId(accessToken);
        String userNick = myJwtUtil.getUserNick(accessToken);
        String userRole = myJwtUtil.getUserRole(accessToken);


        vo.setUserId(userId);
        vo.setUserNick(userNick);
        vo.setUserRole(userRole);


        MyUserDetails principal = new MyUserDetails(vo);
        String credentials = null;
        Collection authorities = principal.getAuthorities();

        Authentication authToken = new UsernamePasswordAuthenticationToken(principal , credentials, authorities);

        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request,response);



    }
}
