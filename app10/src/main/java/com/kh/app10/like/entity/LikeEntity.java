package com.kh.app10.like.entity;

import com.kh.app10.board.entity.BoardEntity;
import com.kh.app10.like.dto.LikeDto;
import com.kh.app10.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name="LIKES", uniqueConstraints =@UniqueConstraint(columnNames = {"memberNo, boardNo"}))
@Getter
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @ManyToOne
    @JoinColumn(name = "memberNo")
    private MemberEntity memberEntity;

    @ManyToOne
    @JoinColumn(name = "boardNo")
    private BoardEntity boardEntity;


    public static LikeEntity from(MemberEntity memberEntity, BoardEntity boardEntity) {
        LikeEntity entity = new LikeEntity();
        entity.memberEntity = memberEntity;
        entity.boardEntity = boardEntity;
        return entity;
    }
}
