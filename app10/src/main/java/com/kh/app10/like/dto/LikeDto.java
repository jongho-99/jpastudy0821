package com.kh.app10.like.dto;

import com.kh.app10.like.entity.LikeEntity;

public class LikeDto {

    private Long no;
    private Long memberNo;
    private Long boardNo;

    public static LikeDto from(LikeEntity entity) {

        LikeDto dto = new LikeDto();
        dto.no = entity.getNo();
        dto.memberNo = entity.getMemberEntity().getNo();
        dto.boardNo = entity.getBoardEntity().getNo();

        return dto;
    }
}
