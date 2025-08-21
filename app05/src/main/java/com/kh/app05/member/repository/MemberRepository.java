package com.kh.app05.member.repository;

import com.kh.app05.board.dto.BoardDto;
import com.kh.app05.board.entity.BoardEntity;
import com.kh.app05.member.dto.MemberDto;
import com.kh.app05.member.entity.MemberEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void join(MemberEntity entity) {
        em.persist(entity);
    }

    public MemberEntity findById(Long writerNo) {
        return em.find(MemberEntity.class, writerNo);
    }

    public MemberEntity login(MemberDto dto) {
        String jpql = "SELECT m FROM MemberEntity m WHERE m.userId = :userId AND m.userPwd = :userPwd";
        return em.createQuery(jpql, MemberEntity.class)
                .setParameter("userId", dto.getUserId())
                .setParameter("userPwd", dto.getUserPwd())
                .getSingleResult();
    }

    public List<MemberEntity> findAllMember() {
        String jpql = "select m from MemberEntity m";
        return em.createQuery(jpql , MemberEntity.class)
                .getResultList();
    }


}
