package com.item.recommend.service.port;

import com.item.category.domain.Category;
import com.item.recommend.domain.Recommend;

public interface RecommendRepository {
    Recommend getLowPriceGroupByCategory();
    Recommend getLowPriceAllCategoryByBrand();
    Recommend getHighestAndLowestPriceByCategory(Category category);
}
