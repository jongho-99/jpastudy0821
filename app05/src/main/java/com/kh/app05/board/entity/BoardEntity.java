package com.kh.app05.board.entity;

import com.kh.app05.board.dto.BoardDto;
import com.kh.app05.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name="BOARD")
@SequenceGenerator(name = "board_seq_gen", sequenceName = "SEQ_BOARD", allocationSize = 1)
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq_gen")
    private long no;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    private LocalDateTime createAt;

    @Column(length = 1)
    private String delYn;

    public BoardEntity() {
        createAt = LocalDateTime.now();
        delYn = "N";
    }

    @ManyToOne
    @JoinColumn(name = "writerNo")
    private MemberEntity writer;

    public BoardEntity(BoardDto dto) {
        no = dto.getNo();
        title = dto.getTitle();
        content = dto.getContent();
        createAt = dto.getCreateAt();
        delYn = dto.getDelYn();
    }

    public static BoardEntity from(BoardDto dto, MemberEntity memberEntity) {

        BoardEntity entity = new BoardEntity();
        entity.no = dto.getNo();
        entity.title = dto.getTitle();
        entity.content = dto.getContent();
        entity.writer = memberEntity;

        return entity;
    }

    public void setWriter(MemberEntity writer) {
        this.writer = writer;
    }



    public void delete() {
        if(delYn.equals("Y")) {
            throw new IllegalStateException("이미 삭제된 게시글 입니다 ㅋㅋ");
        }
        delYn = "Y";
    }

//    public static BoardEntity toEntity(BoardDto dto) {
//
//    }
}
