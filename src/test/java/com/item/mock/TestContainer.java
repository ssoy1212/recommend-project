package com.item.mock;

import com.item.brand.controller.BrandController;
import com.item.brand.controller.port.BrandService;
import com.item.brand.service.BrandServiceImpl;
import com.item.brand.service.port.BrandRepository;
import com.item.category.service.port.CategoryRepository;
import com.item.product.controller.ProductController;
import com.item.product.controller.port.ProductService;
import com.item.product.service.ProductServiceImpl;
import com.item.product.service.port.ProductRepository;
import lombok.Builder;

public class TestContainer {
    public final BrandRepository brandRepository;
    public final BrandService brandService;
    public final BrandController brandController;

    public final ProductRepository productRepository;
    public final ProductService productService;
    public final ProductController productController;

    public final CategoryRepository categoryRepository;

    @Builder
    public TestContainer() {
        this.brandRepository = new TestBrandRepository();

        this.brandService = BrandServiceImpl.builder()
                .brandRepository(this.brandRepository)
                .build();
        this.brandController = BrandController.builder()
                .brandService(this.brandService).build();

        this.productRepository = new TestProductRepository();
        this.productService = ProductServiceImpl.builder()
                .productRepository(this.productRepository)
                .build();
        this.productController = ProductController.builder()
                .productService(this.productService).build();

        this.categoryRepository = new TestCategoryRepository();


    }
}
