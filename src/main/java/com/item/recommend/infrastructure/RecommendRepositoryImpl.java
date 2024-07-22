package com.item.recommend.infrastructure;

import com.item.category.domain.Category;
import com.item.common.exception.RecommendNotFoundException;
import com.item.recommend.domain.Recommend;
import com.item.recommend.domain.RecommendItems;
import com.item.recommend.service.port.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecommendRepositoryImpl implements RecommendRepository {

    private final RecommendJpaRepository recommendJpaRepository;

    @Override
    public Recommend getLowPriceGroupByCategory() {
        List<RecommendResponsesEntity> responses = recommendJpaRepository.getLowPriceGroupByCategory()
                .filter(s -> !s.isEmpty())
                .orElseThrow(RecommendNotFoundException::new);

        List<RecommendItems> items = responses.stream()
                .map( e -> RecommendItems.builder()
                        .categoryName(e.getCategoryName())
                        .brandName(e.getBrandName())
                        .price(e.getPrice())
                        .build()
                ).toList();

       return Recommend.makeItems(responses.get(0).getTotalPrice(),items);
    }

    @Override
    public Recommend getLowPriceAllCategoryByBrand() {
        List<RecommendResponsesEntity> responses = recommendJpaRepository.getLowPriceAllCategoryByBrand()
                .filter(s -> !s.isEmpty())
                .orElseThrow(RecommendNotFoundException::new);

        List<RecommendItems> items = responses.stream()
                .map( e -> RecommendItems.builder()
                        .categoryName(e.getCategoryName())
                        .price(e.getPrice())
                        .build()
                ).toList();

        return Recommend.makeItems(responses.get(0).getTotalPrice(),responses.get(0).getBrandName(),items);
    }

    @Override
    public Recommend getHighestAndLowestPriceByCategory(Category category) {

        List<RecommendResponsesEntity> responses = recommendJpaRepository.getHighestAndLowestPriceByCategory(category.getId())
                .filter(s -> !s.isEmpty())
                .orElseThrow(RecommendNotFoundException::new);

        List<RecommendItems> items = responses.stream()
                .map( e -> RecommendItems.builder()
                        .brandName(e.getBrandName())
                        .priceType(e.getPriceType())
                        .price(e.getPrice())
                        .build()
                ).toList();

        return Recommend.makeItems(category.getName(),items);
    }
}
