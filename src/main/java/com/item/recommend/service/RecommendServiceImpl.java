package com.item.recommend.service;

import com.item.category.domain.Category;
import com.item.category.service.port.CategoryRepository;
import com.item.recommend.controller.port.RecommendService;
import com.item.recommend.domain.Recommend;
import com.item.recommend.service.port.RecommendRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Builder
@RequiredArgsConstructor
public class RecommendServiceImpl implements RecommendService {

    private static final Logger log = LoggerFactory.getLogger(RecommendServiceImpl.class);
    private final RecommendRepository recommendRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public Recommend getLowPriceGroupByCategory() {
        return recommendRepository.getLowPriceGroupByCategory();
    }

    @Override
    @Transactional(readOnly = true)
    public Recommend getLowPriceAllCategoryByBrand() {
        return recommendRepository.getLowPriceAllCategoryByBrand();
    }

    @Override
    @Transactional(readOnly = true)
    public Recommend getHighestAndLowestPriceByCategory(String request ) {

        Category category = categoryRepository.findByNameIgnoreCase(request);

        return recommendRepository.getHighestAndLowestPriceByCategory(category);
    }
}
