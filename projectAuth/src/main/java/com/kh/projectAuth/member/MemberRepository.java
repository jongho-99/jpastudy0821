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


    //토큰이 없다는걸 파악하고 첫 로그인에만 해당 login메서드 사용
    public MemberEntity login(MemberEntity entity) {

        String jpql = "SELECT m FROM MemberEntity m WHERE m.userId = :userId AND m.userPwd = :userPwd";

        return em.createQuery(jpql, MemberEntity.class)
                .setParameter("userId", entity.getUserId())
                .setParameter("userPwd", entity.getUserPwd())
                .getSingleResult();
    }

    public MemberEntity findUserById(String name) {
        String jpql = "SELECT m FROM MemberEntity m WHERE m.userId = :userId AND m.delYn = 'N'";
        return em.createQuery(jpql, MemberEntity.class)
                .setParameter("userId", name)
                .getSingleResult();
    }



}
