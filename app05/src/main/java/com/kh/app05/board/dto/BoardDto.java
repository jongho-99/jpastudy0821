package com.kh.app05.board.dto;

import com.kh.app05.board.entity.BoardEntity;
import com.kh.app05.member.entity.MemberEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDto {

    private long no;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private String delYn;
    private Long writerNo;

    public static BoardDto from(BoardEntity boardEntity) {
        BoardDto dto = new BoardDto();
        dto.no = boardEntity.getNo();
        dto.title = boardEntity.getTitle();
        dto.content = boardEntity.getContent();
        dto.createAt = boardEntity.getCreateAt();
        dto.delYn = boardEntity.getDelYn();
        dto.writerNo = boardEntity.getWriter().getNo();

        return dto;
    }

}
