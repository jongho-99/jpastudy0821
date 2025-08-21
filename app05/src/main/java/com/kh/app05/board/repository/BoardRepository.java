package com.kh.app05.board.repository;

import com.kh.app05.board.dto.BoardDto;
import com.kh.app05.board.entity.BoardEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.plaf.SpinnerUI;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    public void insert(BoardEntity entity) {
        em.persist(entity);
    }

    public List<BoardEntity> findBoardAll() {
        String jpql = "select b from BoardEntity b";

        return em.createQuery(jpql, BoardEntity.class)
                .getResultList();
    }

    public BoardEntity findBoardByNo(Long no) {
        return em.find(BoardEntity.class, no);
    }


}
