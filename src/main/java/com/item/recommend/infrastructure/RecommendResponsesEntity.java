package com.item.recommend.infrastructure;

public interface RecommendResponsesEntity {
    Long getBrandId();
    String getBrandName();
    Long getCategoryId();
    String getCategoryName();
    Long getTotalPrice();
    Long getPrice();
    Long getHighPrice();
    Long getLowestPrice();
    String getPriceType();
}
