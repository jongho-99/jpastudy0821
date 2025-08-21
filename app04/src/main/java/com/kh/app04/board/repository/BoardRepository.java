package com.kh.app04.board.repository;

import com.kh.app04.board.entity.BoardEntity;
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

    public List<BoardEntity> selectList() {
        return em.createQuery("SELECT b FROM BoardEntity b WHERE b.delYn = 'N'", BoardEntity.class)
                .getResultList();
    }
}
