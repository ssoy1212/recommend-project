package com.item.recommend.controller.response;

import com.item.recommend.domain.Recommend;
import com.item.recommend.domain.RecommendItems;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RecommendResponse {
    private final String categoryName;
    private final List<RecommendItems> HighestPrice;
    private final List<RecommendItems> LowestPrice;

    @Builder
    public RecommendResponse(String categoryName, Recommend recommend){
        this.categoryName = categoryName;
        this.HighestPrice = setHighestPrice(recommend);
        this.LowestPrice = setLowestPrice(recommend);
    }

    private static List<RecommendItems> setHighestPrice(Recommend recommend) {

        List<RecommendItems> highestPrice = new ArrayList<>();
        highestPrice.add(recommend.getItems().stream().filter(e-> e.getPriceType().equals("MAX")).findFirst().get());
        return highestPrice;
    }

    private static List<RecommendItems> setLowestPrice(Recommend recommend) {
        List<RecommendItems> lowestPrice = new ArrayList<>();
        lowestPrice.add(recommend.getItems().stream().filter(e-> e.getPriceType().equals("MIN")).findFirst().get());
        return lowestPrice;
    }
}
