package com.kh.app10.board.repository;

import com.kh.app10.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    //성능 최적화 코드임니다 (LAZY + JOIN FETCH)
    @Query("""
            SELECT b
            FROM BoardEntity b
            JOIN FETCH b.writer
            LEFT JOIN FETCH b.likeEntityList l
            LEFT JOIN FETCH l.memberEntity
            """)
    List<BoardEntity> findAllWithLikeEntityAndMemberEntity();
}
