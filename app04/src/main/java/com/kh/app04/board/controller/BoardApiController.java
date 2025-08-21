package com.kh.app04.board.controller;

import com.kh.app04.board.dto.BoardDto;
import com.kh.app04.board.entity.BoardEntity;
import com.kh.app04.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/board")
@RequiredArgsConstructor

public class BoardApiController {

    private final BoardService boardService;

    @PostMapping
    public void insert(@RequestBody BoardDto dto) {
        String loginMemberId = "test01";
        boardService.insert(dto, loginMemberId);
    }

    @GetMapping
    public List<BoardEntity> selectList() {
        return boardService.selectList();
    }



}
