package com.item.brand.service.service;

import com.item.brand.domain.Brand;
import com.item.brand.domain.BrandRequest;
import com.item.brand.service.BrandServiceImpl;
import com.item.common.exception.BrandNotFoundException;
import com.item.mock.TestBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BrandServiceTest {

    private BrandServiceImpl brandService;

    @BeforeEach
    void init() {
        TestBrandRepository brandRepository = new TestBrandRepository();
        this.brandService = BrandServiceImpl.builder()
                .brandRepository(brandRepository)
                .build();
        Brand data1 = Brand.builder()
                .id(1L)
                .name("BRAND_A")
                .viewRank(1L)
                .build();
        Brand data2 = Brand.builder()
                .id(2L)
                .name("BRAND_B")
                .viewRank(1L)
                .build();
        brandRepository.save(data1);
        brandRepository.save(data2);
    }

    @Test
    void getById() {
        // given
        // when
        Brand brand = brandService.getById(1L);
        // then
        assertThat(brand.getName()).isEqualTo("BRAND_A");
    }

    @Test
    void create() {
        // given
        BrandRequest brandRequest = BrandRequest.builder()
                .name("BRAND_C")
                .viewRank(1L)
                .build();

        // when
        Brand result = brandService.create(brandRequest);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo("BRAND_C");
    }

    @Test
    void update() {
        // given
        BrandRequest brandRequest = BrandRequest.builder()
                .id(1L)
                .name("BRAND_C")
                .viewRank(1L)
                .build();

        // when
        brandService.update(brandRequest);

        // then
        Brand brand = brandService.getById(1L);
        assertThat(brand.getName()).isEqualTo("BRAND_C");
    }

    @Test
    void delete() {
        // given
        BrandRequest brandRequest = BrandRequest.builder()
                .id(1L)
                .build();
        // when
        brandService.delete(brandRequest);
        // then
        assertThatThrownBy(() -> {
            brandService.getById(1L);
        }).isInstanceOf(BrandNotFoundException.class);

    }
}