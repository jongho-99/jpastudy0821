package com.kh.app10.member.service;

import com.kh.app10.member.dto.MemberReqDto;
import com.kh.app10.member.dto.MemberRespDto;
import com.kh.app10.member.entity.MemberEntity;
import com.kh.app10.member.exception.MemberException;
import com.kh.app10.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(MemberReqDto dto) {
        MemberEntity entity = MemberEntity.from(dto);
        memberRepository.save(entity);
    }

    public MemberRespDto login(MemberReqDto dto) {
        MemberEntity entity = memberRepository.findByUserIdAndDelYn(dto.getUserId(), "N");
        if(!entity.getUserPwd().equals(dto.getUserPwd())) {
            throw new MemberException("PWD NOT MATCH");
        }

        return MemberRespDto.from(entity);
    }
}
