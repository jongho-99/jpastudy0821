package com.kh.app05.board.controller;

import com.kh.app05.board.dto.BoardDto;
import com.kh.app05.board.entity.BoardEntity;
import com.kh.app05.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public void insert(@RequestBody BoardDto dto) {
            dto.setWriterNo(3L);
            boardService.insert(dto);
    }

    @GetMapping
    public List<BoardDto> findBoardAll() {
        return boardService.findBoardAll();

    }

    @GetMapping("{no}")
    public BoardDto findBoardOne(@PathVariable Long no) {
        return boardService.findBoardByNo(no);

    }

    @DeleteMapping("{no}")
    public void deleteBoardByNo(@PathVariable Long no) {
        boardService.deleteBoardByNo(no);
    }

}
