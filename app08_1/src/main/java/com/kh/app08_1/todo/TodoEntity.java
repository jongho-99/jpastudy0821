package com.kh.app08_1.todo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kh.app08_1.category.CategoryEntity;
import com.kh.app08_1.state.StateEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "TODO")
@Getter
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false, length = 100)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stateNo", nullable = false)
    private StateEntity state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryNo", nullable = false)
    private CategoryEntity category;

    @Column(nullable = false, length = 1)
    private String delYn;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TodoEntity() {
        delYn = "N";
        createdAt = LocalDateTime.now();
    }

    public static TodoEntity from(TodoDto dto, StateEntity sEntity, CategoryEntity cEntity) {
        TodoEntity entity = new TodoEntity();

        entity.no = dto.getNo();
        entity.title = dto.getTitle();
        entity.content = dto.getContent();

        entity.state = sEntity;
        entity.category = cEntity;

        return entity;
    }

    public void update(TodoDto dto, StateEntity sEntity, CategoryEntity cEntity) {
        title = dto.getTitle();
        content = dto.getContent();
        state = sEntity;
        category = cEntity;
        updatedAt = LocalDateTime.now();
    }

    public void delete() {
        delYn = "Y";
    }
}
