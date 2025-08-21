package com.kh.app04.board.service;

import com.kh.app04.board.dto.BoardDto;
import com.kh.app04.board.entity.BoardEntity;
import com.kh.app04.board.repository.BoardRepository;
import com.kh.app04.member.entity.MemberEntity;
import com.kh.app04.member.repository.MemberRepository;
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

    public void insert(BoardDto dto, String loginMemberId) {

        BoardEntity entity = new BoardEntity();

        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());

        MemberEntity memberEntity = memberRepository.getMemberByUserId(loginMemberId);


        entity.setWriter(memberEntity);
        boardRepository.insert(entity);
    }

    public List<BoardEntity> selectList() {
        return boardRepository.selectList();
    }
}
