package com.kh.app10.board.service;

import com.kh.app10.board.dto.BoardReqDto;
import com.kh.app10.board.dto.BoardResqDto;
import com.kh.app10.board.entity.BoardEntity;
import com.kh.app10.board.repository.BoardRepository;
import com.kh.app10.like.repository.LikeRepository;
import com.kh.app10.member.entity.MemberEntity;
import com.kh.app10.member.repository.MemberRepository;
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
    private final LikeRepository likeRepository;

    public void insert(BoardReqDto reqDto, String userId) {
        MemberEntity loginEntity = memberRepository.findByUserIdAndDelYn(userId, "N");
        BoardEntity entity = BoardEntity.from(reqDto, loginEntity);

        boardRepository.save(entity);
    }

    public List<BoardResqDto> findBoardByAll() {

        List<BoardEntity> boardEntityList =  boardRepository.findAllWithLikeEntityAndMemberEntity();
        return boardEntityList.stream().map(BoardResqDto::from).toList();
    }

    public BoardResqDto findBoardByNo(Long no) {
        BoardEntity entity = boardRepository.findById(no).get();
        Long cnt = likeRepository.countByBoardEntity(entity);
        BoardResqDto boardResqDto=  BoardResqDto.from(entity);
        boardResqDto.setLikeCnt(cnt);
        return boardResqDto;
    }



    public void updateBoard(BoardReqDto reqDto, Long no) {

        BoardEntity entity = boardRepository.findById(no).get();
        entity.update(reqDto);

    }


    public void deleteBoard(Long no) {
        BoardEntity entity = boardRepository.findById(no).get();
        entity.delete();
    }
}
