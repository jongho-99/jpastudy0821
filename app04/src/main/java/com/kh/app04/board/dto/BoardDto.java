package com.kh.app04.board.dto;

import com.kh.app04.board.entity.BoardEntity;
import lombok.Data;

@Data
public class BoardDto {

    private long no;
    private String title;
    private String content;
    private String writerNo;


    public static BoardDto from(BoardEntity entity) {

        BoardDto dto = new BoardDto();
        dto.setNo(entity.getNo());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());

        return dto;
    }
}
