package com.kh.app06.member.entity;

import com.kh.app06.member.dto.MemberDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBER")
@Getter
@Setter(AccessLevel.PRIVATE)
@SequenceGenerator(name = "memberSeqGen", sequenceName = "SEQ_MEMBER", allocationSize = 1)
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "memberSeqGen")
    private Long no;
    @Column(nullable = false, unique = true, length = 100)
    private String userId;
    @Column(nullable = false, length = 100)
    private String userPwd;
    @Column(length = 100)
    private String userNick;
    private LocalDateTime createAt;
    @Column(length = 1)
    private String delYn;


    public MemberEntity() {
        createAt = LocalDateTime.now();
        delYn = "N";
    }

    public static MemberEntity from(MemberDto dto) {
        MemberEntity entity = new MemberEntity();

        //Entity에서 유효성 검사 도메인 방식 DDD
        if (dto.getUserId().length() > 100) {
            throw new IllegalArgumentException("id length errrr");
        }

        entity.setUserId(dto.getUserId());
        entity.setUserPwd(dto.getUserPwd());
        entity.setUserNick(dto.getUserNick());

        return entity;
    }
}
