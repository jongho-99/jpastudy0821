package com.kh.app10.member.entity;

import com.kh.app10.like.entity.LikeEntity;
import com.kh.app10.member.dto.MemberDto;
import com.kh.app10.member.dto.MemberReqDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "MEMBER")
@Getter
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(unique = true, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userPwd;

    @Column(nullable = false)
    private String userNick;

    private String delYn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "memberEntity", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<LikeEntity> likeEntityList;

    public MemberEntity() {

        delYn = "N";
        createdAt = LocalDateTime.now();
    }

    public static MemberEntity from(MemberReqDto dto) {
        MemberEntity entity = new MemberEntity();

        entity.userId = dto.getUserId();
        entity.userPwd = dto.getUserPwd();
        entity.userNick = dto.getUserNick();

        return entity;
    }
}