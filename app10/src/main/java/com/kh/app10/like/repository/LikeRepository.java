package com.kh.app10.like.repository;

import com.kh.app10.board.entity.BoardEntity;
import com.kh.app10.like.entity.LikeEntity;
import com.kh.app10.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    LikeEntity findByMemberEntityAndBoardEntity(MemberEntity memberEntity, BoardEntity boardEntity);

    Boolean existsByMemberEntityAndBoardEntity(MemberEntity memberEntity, BoardEntity boardEntity);

    void deleteByMemberEntityAndBoardEntity(MemberEntity memberEntity, BoardEntity boardEntity);

    Long countByBoardEntity(BoardEntity entity);
}
