package com.item.recommend.controller;

import com.item.common.exception.BaseException;
import com.item.common.exception.ErrorCode;
import com.item.recommend.controller.port.RecommendService;
import com.item.recommend.controller.response.RecommendResponse;
import com.item.recommend.domain.Recommend;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/recommend")
@Builder
@RequiredArgsConstructor
public class RecommendController {

    private final RecommendService recommendService;

    @GetMapping("/getLowPriceGroupByCategory")
    public ResponseEntity<Recommend> getLowPriceGroupByCategory() {
       return ResponseEntity.ok()
                .body(recommendService.getLowPriceGroupByCategory());
    }

    @GetMapping("/getLowPriceAllCategoryByBrand")
    public ResponseEntity<Recommend> getLowPriceAllCategoryByBrand() {
        return ResponseEntity.ok()
                .body(recommendService.getLowPriceAllCategoryByBrand());
    }

    @GetMapping("/getHighestAndLowestPriceByCategory/{categoryName}")
    public ResponseEntity<RecommendResponse> getHighestAndLowestPriceByCategory(@PathVariable String categoryName) {
        if(categoryName.isEmpty()) {
            throw new BaseException(ErrorCode.REQUIRED_CATEGORY_NAME);
        }
        Recommend result =  recommendService.getHighestAndLowestPriceByCategory(categoryName);
        return ResponseEntity.ok()
                .body(RecommendResponse.builder()
                        .categoryName(result.getCategoryName())
                        .recommend(result)
                        .build());
    }

}
