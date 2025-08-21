package com.kh.app05.member.entity;

import com.kh.app05.board.entity.BoardEntity;
import com.kh.app05.member.dto.MemberDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Table(name="MEMBER")
@SequenceGenerator(name = "member_seq_gen", sequenceName = "SEQ_MEMBER", allocationSize = 1)
public class MemberEntity {

    @Id
    @GeneratedValue(generator = "member_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long no;

    // primitive long x Long o (null 값을 수용함)

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String userPwd;

    @Column(nullable = false)
    private String userNick;

    private LocalDateTime createAt;

    @Column(length = 1)
    private String delYn;

    @OneToMany(mappedBy = "writer")
    private List<BoardEntity> boardEntityList;

    public void addBoardEntity(BoardEntity boardEntity) {
        this.boardEntityList.add(boardEntity);
        boardEntity.setWriter(this);
    }

    public MemberEntity() {
        createAt = LocalDateTime.now();
        delYn = "N";
    }

    public static  MemberEntity from(MemberDto dto) {

        MemberEntity entity = new MemberEntity();
        entity.no = dto.getNo();
        entity.userId = dto.getUserId();
        entity.userPwd = dto.getUserPwd();
        entity.userNick = dto. getUserNick();

        return entity;
    }


//    public void setDelYn(String delYn) {
//        if(delYn.equals("Y") || delYn.equals("N")) {
//            this.delYn = delYn;
//        } else {
//            throw new IllegalArgumentException();
//        }
//    }

}
