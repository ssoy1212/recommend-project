package com.item.category.infrastructure;

import com.item.category.domain.Category;
import com.item.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;


    public Category toModel() {
        return Category.builder()
                .id(id)
                .name(name)
                .build();
    }
}
