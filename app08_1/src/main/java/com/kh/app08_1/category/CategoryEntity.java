package com.kh.app08_1.category;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name="CATEGORY")
@Getter
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryNo;

    private String categoryName;
}
