package com.kh.app10.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kh.app10.board.entity.BoardEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardReqDto {

    private String title;
    private String content;



}
