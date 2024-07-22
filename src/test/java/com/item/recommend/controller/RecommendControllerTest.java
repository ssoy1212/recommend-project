package com.item.recommend.controller;

import com.item.recommend.controller.response.RecommendResponse;
import com.item.recommend.domain.Recommend;
import com.item.recommend.domain.RecommendItems;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class RecommendControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void 카테고리별_최저가격_브랜드와_상품_및_총액() throws Exception {
        List<RecommendItems> items = new ArrayList<>();
        items.add(RecommendItems.builder().brandName("BRAND_C").categoryName("Top").price(10000L).build());
        items.add(RecommendItems.builder().brandName("BRAND_E").categoryName("Outer").price(5000L).build());
        items.add(RecommendItems.builder().brandName("BRAND_D").categoryName("Bottom").price(3000L).build());
        items.add(RecommendItems.builder().brandName("BRAND_G").categoryName("Sneakers").price(9000L).build());
        items.add(RecommendItems.builder().brandName("BRAND_A").categoryName("Bag").price(2000L).build());
        items.add(RecommendItems.builder().brandName("BRAND_D").categoryName("Hat").price(1500L).build());
        items.add(RecommendItems.builder().brandName("BRAND_I").categoryName("Socks").price(1700L).build());
        items.add(RecommendItems.builder().brandName("BRAND_F").categoryName("Accessories").price(1900L).build());
        Recommend recommend = Recommend.makeItems(34100L,"BRAND_D",items);
        // when
        final ResultActions result = mockMvc.perform(get("/recommend/getLowPriceGroupByCategory")
                .accept(MediaType.APPLICATION_JSON));
        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice").value(recommend.getTotalPrice()))
                .andExpect(jsonPath("$.items[0].categoryName").value(recommend.getItems().get(0).getCategoryName()))
                .andExpect(jsonPath("$.items[0].brandName").value(recommend.getItems().get(0).getBrandName()))
                .andExpect(jsonPath("$.items[0].price").value(recommend.getItems().get(0).getPrice()));
    }

    @Test
    void 최저가격에_판매하는_브랜드의_총액과_상품리스트() throws Exception {
        List<RecommendItems> items = new ArrayList<>();
        items.add(RecommendItems.builder().categoryName("Top").price(10100L).build());
        items.add(RecommendItems.builder().categoryName("Outer").price(5100L).build());
        items.add(RecommendItems.builder().categoryName("Bottom").price(3000L).build());
        items.add(RecommendItems.builder().categoryName("Sneakers").price(9500L).build());
        items.add(RecommendItems.builder().categoryName("Bag").price(2500L).build());
        items.add(RecommendItems.builder().categoryName("Hat").price(1500L).build());
        items.add(RecommendItems.builder().categoryName("Socks").price(2400L).build());
        items.add(RecommendItems.builder().categoryName("Accessories").price(2000L).build());
        Recommend recommend = Recommend.makeItems(36100L,"BRAND_D",items);
        // when
        final ResultActions result = mockMvc.perform(get("/recommend/getLowPriceAllCategoryByBrand")
                .accept(MediaType.APPLICATION_JSON));
        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice").value(recommend.getTotalPrice()))
                .andExpect(jsonPath("$.brandName").value(recommend.getBrandName()))
                .andExpect(jsonPath("$.items[0].categoryName").value(recommend.getItems().get(0).getCategoryName()))
                .andExpect(jsonPath("$.items[0].price").value(recommend.getItems().get(0).getPrice()));
    }

    @Test
    void 카테고리이름으로_최저_최고가격_브랜드와_상품_조회() throws Exception {
        List<RecommendItems> items = new ArrayList<>();
        items.add(RecommendItems.builder().brandName("BRAND_I")
                        .priceType("MAX")
                        .price(11400L)
                        .build());
        items.add(RecommendItems.builder()
                        .brandName("BRAND_C")
                        .priceType("MIN")
                        .price(10000L)
                        .build());
        Recommend recommend = Recommend.makeItems("Top",items);
        RecommendResponse response = RecommendResponse.builder()
                .categoryName("Top")
                .recommend(recommend)
                .build();
        // when
        final ResultActions result = mockMvc.perform(get("/recommend/getHighestAndLowestPriceByCategory/{name}","Top")
                .accept(MediaType.APPLICATION_JSON));
        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryName").value(response.getCategoryName()))
                .andExpect(jsonPath("$.highestPrice[0].priceType").value(recommend.getItems().get(0).getPriceType()))
                .andExpect(jsonPath("$.highestPrice[0].price").value(recommend.getItems().get(0).getPrice()))
                .andExpect(jsonPath("$.highestPrice[0].brandName").value(recommend.getItems().get(0).getBrandName()))
                .andExpect(jsonPath("$.lowestPrice[0].priceType").value(recommend.getItems().get(1).getPriceType()))
                .andExpect(jsonPath("$.lowestPrice[0].price").value(recommend.getItems().get(1).getPrice()))
                .andExpect(jsonPath("$.lowestPrice[0].brandName").value(recommend.getItems().get(1).getBrandName()));
    }
}