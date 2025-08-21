package com.kh.app06.board.repository;

import com.kh.app06.board.dto.BoardDto;
import com.kh.app06.board.entity.BoardEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;
    public void insert(BoardEntity entity) {
        em.persist(entity);
    }

    public BoardEntity findBoardByNo(Long no) {
        return em.find(BoardEntity.class,no);
    }

    public List<BoardEntity> findBoardList() {
        return em.createQuery("SELECT b FROM BoardEntity b WHERE b.delYn = 'N' order by b.no desc",
                BoardEntity.class)
                .getResultList();
    }
}
