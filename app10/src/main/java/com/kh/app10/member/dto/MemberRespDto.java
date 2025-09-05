package com.kh.app10.member.dto;

import com.kh.app10.board.dto.BoardResqDto;
import com.kh.app10.like.dto.LikeDto;
import com.kh.app10.member.entity.MemberEntity;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MemberRespDto {

    private String userId;
    private String userNick;

    private List<Long> likeBoardNoList;
    private List<String> likeBoardTitleList;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static MemberRespDto from(MemberEntity entity) {
        MemberRespDto dto = new MemberRespDto();
        dto.userId = entity.getUserId();
        dto.userNick = entity.getUserNick();

        dto.likeBoardNoList = entity.getLikeEntityList()
                .stream()
                .map(likeEntity -> likeEntity.getBoardEntity().getNo())
                .toList();

        dto.likeBoardTitleList = entity.getLikeEntityList()
                .stream()
                .map(likeEntity -> likeEntity.getBoardEntity().getTitle())
                .toList();

        dto.createdAt = entity.getCreatedAt();
        dto.updatedAt = entity.getUpdatedAt();

        return dto;
    }
}
