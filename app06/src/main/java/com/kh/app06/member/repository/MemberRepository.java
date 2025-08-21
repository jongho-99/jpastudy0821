package com.kh.app06.member.repository;

import com.kh.app06.member.dto.MemberDto;
import com.kh.app06.member.entity.MemberEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    public void join(MemberEntity entity) {
        em.persist(entity);
    }

    public MemberEntity login(MemberDto dto) {
        String jpql = "SELECT m FROM MemberEntity m WHERE m.userId = :userId AND m.userPwd = :userPwd";
        return em.createQuery(jpql, MemberEntity.class)
                .setParameter("userId", dto.getUserId())
                .setParameter("userPwd", dto.getUserPwd())
                .getSingleResult();
    }

    public List<MemberEntity> list() {
        String jpql = "SELECT m FROM MemberEntity m WHERE m.delYn = 'N' order by m.no desc";
        return em.createQuery(jpql, MemberEntity.class)
                .getResultList();
    }

    public MemberEntity findById(Long no) {
        return em.find(MemberEntity.class, no);
    }
}
