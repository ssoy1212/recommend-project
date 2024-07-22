package com.item.brand.infrastructure;

import com.item.brand.domain.Brand;
import com.item.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "brands")
public class BrandEntity extends BaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "view_rank",nullable = false)
    private Long viewRank;

    public static BrandEntity from(Brand brand) {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.id  = brand.getId();
        brandEntity.viewRank = brand.getViewRank();
        brandEntity.name = brand.getName();
        brandEntity.createdDt = brand.getCreatedDt();
        brandEntity.updatedDt = brand.getUpdatedDt();
        return brandEntity;
    }

    public Brand toModel() {
        return Brand.builder()
                .id(id)
                .name(name)
                .viewRank(viewRank)
                .createdDt(createdDt)
                .updatedDt(updatedDt)
                .build();
    }
}
