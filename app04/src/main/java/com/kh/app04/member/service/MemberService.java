package com.kh.app04.member.service;

import com.kh.app04.board.dto.BoardDto;
import com.kh.app04.member.dto.request.MemberJoinReqDto;
import com.kh.app04.member.dto.request.MemberLoginReqDto;
import com.kh.app04.member.dto.response.MemberLoginRespDto;
import com.kh.app04.member.entity.MemberEntity;
import com.kh.app04.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void join(MemberJoinReqDto reqDto) {

        MemberEntity entity = new MemberEntity();

        entity.setUserId(reqDto.getUserId());
        entity.setUserPwd(reqDto.getUserPwd());
        entity.setUserNick(reqDto.getUserNick());

        memberRepository.join(entity);
    }

    public MemberLoginRespDto login(MemberLoginReqDto reqDto) {

        MemberEntity entity = new MemberEntity();

        entity.setUserId(reqDto.getUserId());
        entity.setUserPwd(reqDto.getUserPwd());

        MemberEntity entity_resp = memberRepository.findMemberEntityByUserIdAndUserPwd(entity);

        MemberLoginRespDto respDto = new MemberLoginRespDto();

        respDto.setUserId(entity_resp.getUserId());
        respDto.setUserNick(entity_resp.getUserNick());


        //
        List<BoardDto> dtoList = entity_resp.getBoardEntityList().stream().map(BoardDto::from).toList();


        respDto.setBoardDtoList(dtoList);

        return respDto;
    }
}
