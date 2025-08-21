package com.kh.app05.member.service;

import com.kh.app05.member.dto.MemberDto;
import com.kh.app05.member.entity.MemberEntity;
import com.kh.app05.member.repository.MemberRepository;
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

        System.out.println("dto = " + dto);
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
        List<MemberEntity> memberEntityList = memberRepository.findAllMember();

        List<MemberDto> memberDtoList = memberEntityList.stream().map(MemberDto::from).toList();

        return memberDtoList;
    }

//    public List<MemberEntity> list() {
//    }
}
