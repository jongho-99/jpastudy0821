package com.kh.app08_1.todo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
public class TodoDto {

    private Long no;
    private String title;
    private String content;
    private Long stateNo;
    private Long categoryNo;

    private String stateName;
    private String categoryName;

    private String delYn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    public static TodoDto from(TodoEntity entity) {
        TodoDto dto = new TodoDto();

        dto.no = entity.getNo();
        dto.title = entity.getTitle();
        dto.content = entity.getContent();
        dto.stateNo = entity.getState().getStateNo();
        dto.categoryNo = entity.getCategory().getCategoryNo();

        dto.stateName = entity.getState().getStateName();
        dto.categoryName = entity.getCategory().getCategoryName();

        dto.createdAt = entity.getCreatedAt();
        dto.updatedAt = entity.getUpdatedAt();

        dto.delYn = entity.getDelYn();
        return dto;
    }
}
