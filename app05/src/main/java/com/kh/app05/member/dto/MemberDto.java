package com.kh.app05.member.dto;

import com.kh.app05.board.dto.BoardDto;
import com.kh.app05.member.entity.MemberEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class MemberDto {

    private Long no;
    private String userId;
    private String userPwd;
    private String userNick;
    private String delYn;
    private LocalDateTime createAt;

    private List<BoardDto> boardDtoList;


    public static MemberDto from(MemberEntity entity) {
        MemberDto dto = new MemberDto();

        dto.no = entity.getNo();
        dto.userId = entity.getUserId();
        dto.userPwd = entity.getUserPwd();
        dto.userNick = entity.getUserNick();
        dto.delYn = entity.getDelYn();
        dto.createAt = entity.getCreateAt();

        dto.boardDtoList = entity.getBoardEntityList().stream().map(BoardDto::from).toList();

        return dto;
    }
}
