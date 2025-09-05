package com.kh.app10.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kh.app10.board.entity.BoardEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
public class BoardDto {

    private Long no;
    private String title;
    private String content;
    private Long writerNo;
    private String writerNick;

    private String delYn;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    public static BoardDto from(BoardEntity entity) {
        BoardDto dto = new BoardDto();

        dto.no = entity.getNo();
        dto.title = entity.getTitle();
        dto.content = entity.getContent();
        dto.writerNo = entity.getWriter().getNo();
        dto.writerNick = entity.getWriter().getUserNick();
        dto.delYn = entity.getDelYn();
        dto.createdAt = entity.getCreatedAt();
        dto.updatedAt = entity.getUpdatedAt();

        return dto;
    }
}