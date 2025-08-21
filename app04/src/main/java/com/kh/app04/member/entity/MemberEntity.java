package com.kh.app04.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kh.app04.board.dto.BoardDto;
import com.kh.app04.board.entity.BoardEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Member")
@SequenceGenerator(name = "member_seq_gen", sequenceName = "SEQ_MEMBER", allocationSize = 1)
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_gen")
    private long no;
    private String userId;
    private String userPwd;
    private String userNick;
    private String delYn;

    public MemberEntity() {
        delYn = "N";
    }

    // writer 넘버 이상태로 출력해버리면 무한참조가 일어남 writer도 entity고 거기안에 boardEntityList 가있고 .. 리스트안에 writer가있고 ..
    //그래서 굳이 BoardEntity 제네릭의 리스트를 만드는게 아니라.. BoardDto형태로 만들어서 안에 title, content, writerNo를 넣어서 출력하면
    //문제될 이유가 없음
    @OneToMany(mappedBy = "writer")
    private List<BoardEntity> boardEntityList;
}
