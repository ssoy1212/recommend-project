package com.item.recommend.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemByBrand {
    private String brandName ;
    private Long price;

    @Builder
    public ItemByBrand(String brandName, Long price) {
        this.brandName = brandName;
        this.price = price;
    }
}
