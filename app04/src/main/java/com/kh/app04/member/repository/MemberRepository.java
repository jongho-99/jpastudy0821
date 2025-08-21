package com.kh.app04.member.repository;

import com.kh.app04.member.entity.MemberEntity;
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

    public MemberEntity findMemberEntityByUserIdAndUserPwd(MemberEntity entity) {
        String jpql = "SELECT m FROM MemberEntity m WHERE m.userId = :inputId AND m.userPwd = :inputPwd AND m.delYn = 'N'";

        return em.createQuery(jpql, MemberEntity.class)
                .setParameter("inputId", entity.getUserId())
                .setParameter("inputPwd", entity.getUserPwd())
                .getSingleResult();
    }

    public MemberEntity getMemberByUserId(String loginMemberId) {

        String jpql = "SELECT m FROM MemberEntity m WHERE m.userId = :loginMemberId ANd m.delYn = 'N'";

        return em.createQuery(jpql, MemberEntity.class)
                .setParameter("loginMemberId", loginMemberId)
                .getSingleResult();
    }
}
