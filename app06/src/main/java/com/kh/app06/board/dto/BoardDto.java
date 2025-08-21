package com.kh.app06.board.dto;

import com.kh.app06.board.entity.BoardEntity;
import com.kh.app06.member.entity.MemberEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class BoardDto {

    private Long no;
    private String title;
    private String content;
    private Long writerNo;
    private LocalDateTime createAt;
    private String delYn;

    public static BoardDto from(BoardEntity entity) {
       BoardDto dto = new BoardDto();

       dto.no = entity.getNo();
       dto.title =entity.getTitle();
       dto.content = entity.getContent();
       dto.writerNo = entity.getWriter().getNo();
       dto.createAt = entity.getCreateAt();
       dto.delYn = entity.getDelYn();

       return dto;
    }

    public void setWriterNo(Long writerNo) {
        this.writerNo = writerNo;
    }

//    public static BoardDto from(BoardEntity entity) {
//
//    }
}