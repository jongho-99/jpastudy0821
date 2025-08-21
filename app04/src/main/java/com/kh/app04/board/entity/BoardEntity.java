package com.kh.app04.board.entity;

import com.kh.app04.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "BOARD")
@SequenceGenerator(name = "board_seq_gen", sequenceName = "SEQ_BOARD", allocationSize = 1)
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq_gen")
    private long no;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private String delYn;

    public BoardEntity() {
        createAt = LocalDateTime.now();
        delYn = "N";
    }

    @ManyToOne
    @JoinColumn
    private MemberEntity writer;
}
