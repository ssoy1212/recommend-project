package com.item.product.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductRequest {
    private Long id;
    private String name;
    private Long brandId;
    private Long categoryId;
    private Long viewRank;
    private Long price;

    @Builder
    public ProductRequest( Long id, String name, Long brandId, Long categoryId, Long viewRank, Long price, LocalDateTime createdDt, LocalDateTime updatedDt) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.viewRank = viewRank;
        this.price = price;
    }
    @Builder
    public ProductRequest() {}
}
