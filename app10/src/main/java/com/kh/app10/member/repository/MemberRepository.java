package com.kh.app10.member.repository;

import com.kh.app10.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    MemberEntity findByUserIdAndDelYn(String userId, String delYn);


}
