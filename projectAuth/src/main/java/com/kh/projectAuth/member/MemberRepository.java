package com.kh.projectAuth.member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void join(MemberEntity entity) {
        em.persist(entity);
    }

    public MemberEntity login(MemberEntity entity) {

        String jpql = "SELECT m FROM MemberEntity m WHERE m.userId = :userId AND m.userPwd = :userPwd";

        return em.createQuery(jpql, MemberEntity.class)
                .setParameter("userId", entity.getUserId())
                .setParameter("userPwd", entity.getUserPwd())
                .getSingleResult();


    }


}
