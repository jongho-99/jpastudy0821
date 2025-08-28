package com.kh.projectAuth.member;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBER")
@Getter
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(nullable = false, length = 20)
    private String userId;

    @Column(nullable = false, length = 20)
    private String userPwd;

    @Column(nullable = false, length = 20)
    private String userNick;

    @Column(nullable = false)
    private String role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private String delYn;

    //업체 고유토큰
    private String companyToken;

    //부서코드
    private Long departmentNo;

    public MemberEntity() {
        delYn = "N";
        createdAt = LocalDateTime.now();
    }

    public static MemberEntity from(MemberDto dto) {

        MemberEntity entity = new MemberEntity();


        //Bcrpt 암호화를 위한 객체생성
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();

        entity.userId = dto.getUserId();
        //Bcrpt 클래스의 encode 메서드를 통해서 입력받은 dto 객체에서 비밀번호만 암호화 하면서
        //JPA 형식에 맞춘 Entity 타입으로 변환
        entity.userPwd = bc.encode(dto.getUserPwd());
        entity.userNick = dto.getUserNick();

        entity.role = dto.getRole();

        entity.companyToken = dto.getCompanyToken();
        entity.departmentNo = dto.getDepartmentNo();

        return entity;
    }

}
