package com.kh.app10.board.entity;

import com.kh.app10.board.dto.BoardDto;
import com.kh.app10.board.dto.BoardReqDto;
import com.kh.app10.like.entity.LikeEntity;
import com.kh.app10.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Table(name = "BOARD")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="writerNo", nullable = false)
    private MemberEntity writer;

    private String delYn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "boardEntity",orphanRemoval = true, cascade = CascadeType.ALL)
    private List<LikeEntity> likeEntityList;

    private Long likeCnt;

    public BoardEntity() {
        delYn = "N";
        createdAt = LocalDateTime.now();
    }

    public static BoardEntity from(BoardReqDto dto, MemberEntity memEntity) {
        BoardEntity entity = new BoardEntity();

        entity.title = dto.getTitle();
        entity.content = dto.getContent();
        entity.writer = memEntity;
        return entity;
    }


    public void update(BoardReqDto reqDto) {
        title = reqDto.getTitle();
        content = reqDto.getContent();

        updatedAt = LocalDateTime.now();
    }

    public void delete() {

        delYn = "Y";
    }
}