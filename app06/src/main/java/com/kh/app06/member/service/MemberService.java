package com.kh.app06.member.service;

import com.kh.app06.member.dto.MemberDto;
import com.kh.app06.member.entity.MemberEntity;
import com.kh.app06.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(MemberDto dto) {
        MemberEntity entity = MemberEntity.from(dto);
        memberRepository.join(entity);
    }

    public MemberDto login(MemberDto dto) {
        MemberEntity entity = memberRepository.login(dto);

        //entity -> dto
        MemberDto resp_dto = MemberDto.from(entity);
        return resp_dto;
    }

    public List<MemberDto> list() {
        List<MemberEntity> memberEntityList =  memberRepository.list();
        List<MemberDto> dto = memberEntityList.stream().map(MemberDto::from).toList();

        return dto;
    }
}
