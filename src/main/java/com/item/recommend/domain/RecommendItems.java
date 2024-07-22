package com.item.recommend.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecommendItems {
    private final String brandName ;
    private final String categoryName;
    private final String priceType;
    private final Long price;

    @Builder
    public RecommendItems(String brandName, String categoryName, String priceType, Long price) {
        this.brandName = brandName;
        this.categoryName = categoryName;
        this.priceType = priceType;
        this.price = price;
    }
}
