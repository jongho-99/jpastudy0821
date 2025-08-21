package com.kh.app05.board.service;

import com.kh.app05.board.dto.BoardDto;
import com.kh.app05.board.entity.BoardEntity;
import com.kh.app05.board.repository.BoardRepository;
import com.kh.app05.member.entity.MemberEntity;
import com.kh.app05.member.repository.MemberRepository;
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

        Long writerNo = dto.getWriterNo();

        //멤버 엔티티 찾아오기
        MemberEntity memberEntity = memberRepository.findById(writerNo);

        BoardEntity entity = BoardEntity.from(dto, memberEntity);

            boardRepository.insert(entity);
    }

    public List<BoardDto> findBoardAll() {
        List<BoardEntity> boardEntityList = boardRepository.findBoardAll();
        System.out.println("boardEntityList = " + boardEntityList);

        return boardEntityList.stream().map(BoardDto::from).toList();
    }

    public BoardDto findBoardByNo(Long no) {


        BoardEntity boardEntity = boardRepository.findBoardByNo(no);

        return BoardDto.from(boardEntity);
    }

    public void deleteBoardByNo(Long no) {
        BoardEntity entity = boardRepository.findBoardByNo(no);
        entity.delete();
    }
}
