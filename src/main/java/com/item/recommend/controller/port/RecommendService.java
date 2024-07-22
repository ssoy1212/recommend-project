package com.item.recommend.controller.port;

import com.item.recommend.domain.Recommend;

public interface RecommendService {
    Recommend getLowPriceGroupByCategory();
    Recommend getLowPriceAllCategoryByBrand();
    Recommend getHighestAndLowestPriceByCategory(String request);
}
