package com.kh.app06.board.entity;


import com.kh.app06.board.dto.BoardDto;
import com.kh.app06.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.lang.reflect.Member;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Table(name = "BOARD")
@SequenceGenerator(name = "boardSeqGen", sequenceName = "SEQ_BOARD", allocationSize = 1)
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boardSeqGen")
    private Long no;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "writerNo", nullable = false)
    private MemberEntity writer;

    private LocalDateTime createAt;
    @Column(length = 1)
    private String delYn;

    public BoardEntity() {
        createAt = LocalDateTime.now();
        delYn = "N";
    }

    public static BoardEntity from(BoardDto dto, MemberEntity memberEntity) {
        BoardEntity entity = new BoardEntity();

        entity.no = dto.getNo();
        entity.title = dto.getTitle();
        entity.content = dto.getContent();
        entity.createAt = LocalDateTime.now();
        entity.delYn = "N";
        entity.writer = memberEntity;

        return entity;
    }
}
