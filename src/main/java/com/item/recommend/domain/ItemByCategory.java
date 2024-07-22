package com.item.recommend.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemByCategory {
    private String categoryName;
    private Long price;

    @Builder
    public ItemByCategory(String categoryName, Long price) {
        this.categoryName = categoryName;
        this.price = price;
    }
}
