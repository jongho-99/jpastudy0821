package com.kh.app06.board.service;

import com.kh.app06.board.dto.BoardDto;
import com.kh.app06.board.entity.BoardEntity;
import com.kh.app06.board.repository.BoardRepository;
import com.kh.app06.member.entity.MemberEntity;
import com.kh.app06.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public void insert(BoardDto dto) {

        MemberEntity memberEntity = memberRepository.findById(dto.getWriterNo());
        BoardEntity entity = BoardEntity.from(dto, memberEntity);
        boardRepository.insert(entity);
    }

    public BoardDto findBoardByNo(Long no) {
        BoardEntity entity = boardRepository.findBoardByNo(no);
        //entity -> dto
        BoardDto dto = BoardDto.from(entity);
        return dto;
    }

    public List<BoardDto> findBoardList() {
        List<BoardEntity> entityList =  boardRepository.findBoardList();
        return entityList.stream().map(BoardDto::from).toList();
    }
}
