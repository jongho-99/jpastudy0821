package com.kh.app04.member.dto.response;

import com.kh.app04.board.dto.BoardDto;
import lombok.Data;

import java.util.List;

@Data
public class MemberLoginRespDto {

    private String userId;
    private String userNick;

    private List<BoardDto> boardDtoList;

}
