package com.kh.app10.like.controller;

import com.kh.app10.like.service.LikeService;
import com.kh.app10.member.dto.MemberRespDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/like")
public class LikeApiController {

    private final LikeService likeService;

    @PostMapping("{boardNo}")
    public void save(@PathVariable Long boardNo, HttpSession ss) {
        MemberRespDto loginMember = (MemberRespDto) ss.getAttribute("loginMember");
        String userId = loginMember.getUserId();

        likeService.toggleLike(userId, boardNo);
    }



}
