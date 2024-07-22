package com.item.product.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    private final Long id;
    private final Long brandId;
    private final Long categoryId;
    private final Long price;
    private final Long viewRank;
    private final String name;
    private final LocalDateTime createdDt;
    private final LocalDateTime updatedDt;

    @Builder
    public Product(Long id, Long brandId, Long categoryId, Long price, Long viewRank, String name, LocalDateTime createdDt, LocalDateTime updatedDt) {
        this.id = id;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.price = price;
        this.viewRank = viewRank;
        this.name = name;
        this.createdDt = createdDt;
        this.updatedDt = updatedDt;
    }
    public static Product from(Product product) {
        return Product.builder()
                .name(product.getName())
                .brandId(product.getBrandId())
                .categoryId(product.getCategoryId())
                .viewRank(product.getViewRank())
                .build();
    }

    public Product update(ProductRequest request) {
        return Product.builder()
                .id(id)
                .name(request.getName())
                .viewRank(request.getViewRank())
                .categoryId(request.getCategoryId())
                .brandId(request.getBrandId())
                .price(request.getPrice())
                .createdDt(createdDt)
                .updatedDt(LocalDateTime.now())
                .build();
    }

}
