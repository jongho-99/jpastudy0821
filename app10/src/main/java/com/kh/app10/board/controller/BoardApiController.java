package com.kh.app10.board.controller;

import com.kh.app10.board.dto.BoardReqDto;
import com.kh.app10.board.dto.BoardResqDto;
import com.kh.app10.board.service.BoardService;
import com.kh.app10.member.dto.MemberRespDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping
    public void insert(@RequestBody BoardReqDto reqDto, HttpSession ss) {
        MemberRespDto member = (MemberRespDto) ss.getAttribute("loginMember");
        boardService.insert(reqDto, member.getUserId());
    }

    @GetMapping
    public List<BoardResqDto> findBoardByAll() {
        return boardService.findBoardByAll();
    }

    @GetMapping("{no}")
    public BoardResqDto findBoardByNo(@PathVariable Long no) {
        return boardService.findBoardByNo(no);
    }

    @PutMapping("{no}")
    public void updateBoard(@RequestBody BoardReqDto dto, @PathVariable Long no) {
        boardService.updateBoard(dto, no);
    }

    @DeleteMapping("{no}")
    public void deleteBoard(@PathVariable Long no) {
        boardService.deleteBoard(no);
    }
}
