package com.item.recommend.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Recommend {
    private Long totalPrice;
    private String brandName;
    private String categoryName;
    private List<RecommendItems> items;

    public static Recommend makeItems(String categoryName, List<RecommendItems> items) {
        Recommend recommend = new Recommend();
        recommend.categoryName = categoryName;
        recommend.items = items;
        return recommend;
    }

    public static Recommend makeItems(Long totalPrice, List<RecommendItems> items) {
        Recommend recommend = new Recommend();
        recommend.totalPrice = totalPrice;
        recommend.items = items;
        return recommend;
    }

    public static Recommend makeItems(Long totalPrice, String brandName, List<RecommendItems> items) {
        Recommend recommend = new Recommend();
        recommend.brandName = brandName;
        recommend.totalPrice = totalPrice;
        recommend.items = items;
        return recommend;
    }
}
