package com.kh.app08_1.state;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StateRepository {

    private final EntityManager em;

    public StateEntity findByNo(Long stateNo) {
        return em.find(StateEntity.class, stateNo);
    }

}
