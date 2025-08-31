package com.kh.app08_1.todo;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepository {
    private final EntityManager em;

    public void insert(TodoEntity entity) {
        em.persist(entity);
    }


    public List<TodoEntity> list() {
        return em.createQuery("SELECT t FROM TodoEntity t WHERE t.delYn = 'N'", TodoEntity.class)
                .getResultList();
    }

    public TodoEntity listOne(Long no) {
        return em.find(TodoEntity.class, no);
    }

}
