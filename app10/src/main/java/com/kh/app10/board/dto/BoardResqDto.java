package com.kh.app10.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kh.app10.board.entity.BoardEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BoardResqDto {
    private Long no;
    private String title;
    private String content;
    private String writerNick;

    private Long likeCnt;

    private List<Long> likeMemberNoList;
    private List<String> likeMemberNickList;


    public static BoardResqDto from(BoardEntity entity) {
        BoardResqDto dto = new BoardResqDto();

        dto.no = entity.getNo();
        dto.title = entity.getTitle();
        dto.content = entity.getContent();
        dto.writerNick = entity.getWriter().getUserNick();
        dto.likeMemberNoList = entity.getLikeEntityList().stream()
                .map(likeEntity -> likeEntity.getMemberEntity().getNo())
                .toList();
        dto.likeMemberNickList = entity.getLikeEntityList().stream()
                .map(likeEntity -> likeEntity.getMemberEntity().getUserNick())
                .toList();
        return dto;
    }

}
