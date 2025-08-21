package com.kh.app06.board.controller;

import com.kh.app06.board.dto.BoardDto;
import com.kh.app06.board.service.BoardService;
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
        Long writerNo = 2L;
        dto.setWriterNo(writerNo);

        boardService.insert(dto);
    }

    @GetMapping("{no}")
    public BoardDto findBoardByNo(@PathVariable Long no) {

        return boardService.findBoardByNo(no);
    }

    @GetMapping
    public List<BoardDto> findBoardList () {
        return boardService.findBoardList();
    }
}
