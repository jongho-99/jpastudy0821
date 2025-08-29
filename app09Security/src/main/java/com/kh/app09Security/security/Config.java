package com.kh.app09Security.security;

import com.kh.app09Security.filter.MyJwtFilter;
import com.kh.app09Security.filter.MyLoginFilter;
import com.sun.net.httpserver.HttpsConfigurator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.UrlHandlerFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Config implements WebMvcConfigurer {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final MyJwtUtil myJwtUtil;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {

        AuthenticationManager authManager = authenticationConfiguration.getAuthenticationManager();
        return authManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity hs) throws Exception {
        hs.formLogin(AbstractHttpConfigurer::disable);
        hs.csrf(AbstractHttpConfigurer::disable);
        hs.httpBasic(AbstractHttpConfigurer::disable);
        hs.sessionManagement( x ->  x.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 모든 요청에 대해서 ... token 검사 레츠고
        hs.authorizeHttpRequests(
                x->x
                        .requestMatchers( "/login", "/join", "/home" , "/", "/admin/login").permitAll() //허가(permitAll())
                        .requestMatchers("/api/test/admin/**").hasRole("ADMIN") //인가
                        .anyRequest().authenticated() //인증
        );



        //필터 추가하기 (로그인 필터 앞에다가 ㅇㅇ 신분증을 확인하는 필터임)
        MyJwtFilter myJwtFilter = new MyJwtFilter(myJwtUtil);
        hs.addFilterBefore(myJwtFilter, MyLoginFilter.class);

        //필터 갈아끼우기
        AuthenticationManager authenticationManager = authenticationManager();

        MyLoginFilter myLoginFilter = new MyLoginFilter( authenticationManager, myJwtUtil );
        myLoginFilter.setFilterProcessesUrl("/login");
        hs.addFilterAt(myLoginFilter, UsernamePasswordAuthenticationFilter.class);


        //CORS
        hs.cors(
                corsConfigurer -> corsConfigurer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration conf = new CorsConfiguration();
                        conf.addAllowedOrigin("*");
                        conf.addAllowedMethod("*");

                        //클라가 서버한테 헤더에 담는 내용에 대해서 커스터마이징해서 값을 담아서 요청가능
                        conf.addAllowedHeader("*");

                        //응답헤더보면 여러가지있음 (포스트맨 참조) 추가하면 모든 헤더값 출력
                        conf.addExposedHeader("*");
                        return conf;
                    }
                })
        );

        return hs.build();
    }

}

