package com.item.product.service;


import com.item.brand.domain.Brand;
import com.item.category.domain.Category;
import com.item.common.exception.ProductNotFoundException;
import com.item.mock.TestBrandRepository;
import com.item.mock.TestCategoryRepository;
import com.item.mock.TestProductRepository;
import com.item.product.controller.port.ProductService;
import com.item.product.domain.Product;
import com.item.product.domain.ProductRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ProductServiceTest {
    private ProductService productService;

    @BeforeEach
    void init() {
        TestProductRepository productRepository = new TestProductRepository();
        TestBrandRepository brandRepository = new TestBrandRepository();
        TestCategoryRepository categoryRepository = new TestCategoryRepository();
        this.productService = ProductServiceImpl.builder()
                .productRepository(productRepository)
                .brandRepository(brandRepository)
                .categoryRepository(categoryRepository)
                .build();
        Product data1 = Product.builder()
                .id(1L)
                .brandId(1L)
                .categoryId(1L)
                .name("TOP_A")
                .viewRank(1L)
                .price(1000L)
                .build();
        Product data2 = Product.builder()
                .id(2L)
                .brandId(1L)
                .categoryId(1L)
                .name("TOP_B")
                .viewRank(1L)
                .price(2000L)
                .build();
        productRepository.save(data1);
        productRepository.save(data2);

        Brand data3 = Brand.builder()
                .id(1L)
                .name("BRAND_A")
                .viewRank(1L)
                .build();
        Brand data4 = Brand.builder()
                .id(2L)
                .name("BRAND_B")
                .viewRank(1L)
                .build();
        brandRepository.save(data3);
        brandRepository.save(data4);

        Category category = Category.builder()
                .id(1L)
                .name("Top")
                .build();
        categoryRepository.save(category);
    }

    @Test
    void getProductById() {
        Product product = productService.getProductById(1L);
        // then
        assertThat(product.getName()).isEqualTo("TOP_A");
        assertThat(product.getPrice()).isEqualTo(1000L);
    }

    @Test
    void create() {
        // given
        ProductRequest productRequest =  ProductRequest.builder()
                .brandId(1L)
                .categoryId(1L)
                .name("TOP_C")
                .viewRank(1L)
                .price(3000L)
                .build();

        // when
        Product result = productService.create(productRequest);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo("TOP_C");
    }

    @Test
    void update() {
        // given
        ProductRequest productRequest =  ProductRequest.builder()
                .id(1L)
                .brandId(1L)
                .categoryId(1L)
                .name("TOP_C")
                .viewRank(1L)
                .price(3000L)
                .build();

        // when
        Product result = productService.update(productRequest);

        // then
        Product product = productService.getProductById(1L);
        assertThat(result.getName()).isEqualTo("TOP_C");
    }

    @Test
    void delete() {
        // given
        ProductRequest brandRequest = ProductRequest.builder()
                .id(1L)
                .build();

        // when
        productService.delete(brandRequest);
        // then
        assertThatThrownBy(() -> {
            productService.getProductById(1L);
        }).isInstanceOf(ProductNotFoundException.class);
    }
}