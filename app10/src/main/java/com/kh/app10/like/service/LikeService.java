package com.kh.app10.like.service;

import com.kh.app10.board.entity.BoardEntity;
import com.kh.app10.board.repository.BoardRepository;
import com.kh.app10.like.entity.LikeEntity;
import com.kh.app10.like.repository.LikeRepository;
import com.kh.app10.member.entity.MemberEntity;
import com.kh.app10.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {
    
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    
    public void toggleLike(String userId, Long boardNo) {
        
        MemberEntity memberEntity = memberRepository.findByUserIdAndDelYn(userId, "N");
        BoardEntity boardEntity = boardRepository.findById(boardNo).get();
        
//        LikeEntity dbLikeEntity = likeRepository.findByMemberEntityAndBoardEntity(memberEntity, boardEntity);

        Boolean dbLikeEntityIsExist = likeRepository.existsByMemberEntityAndBoardEntity(memberEntity, boardEntity);
        if(dbLikeEntityIsExist) {
            likeRepository.deleteByMemberEntityAndBoardEntity(memberEntity, boardEntity);
        }
        else{
            LikeEntity likeEntity = LikeEntity.from(memberEntity, boardEntity);
            likeRepository.save(likeEntity);
        }

    }
}
