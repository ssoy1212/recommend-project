package com.item.product.service;

import com.item.brand.service.port.BrandRepository;
import com.item.category.service.port.CategoryRepository;
import com.item.common.exception.BaseException;
import com.item.common.exception.ErrorCode;
import com.item.product.controller.port.ProductService;
import com.item.product.domain.Product;
import com.item.product.domain.ProductRequest;
import com.item.product.service.port.ProductRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Builder
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id);
    }
    @Transactional(readOnly = true)
    public Product findByNameIgnoreCase(String name) {
        return productRepository.findByNameIgnoreCase(name);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Product create(ProductRequest request) {
        Product newProduct = this.validateCreateProduct(request);
        return productRepository.save(newProduct);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Product update(ProductRequest request) {
        this.validateUpdateProduct(request);
        Product product = this.getProductById(request.getId()).update(request);
        return productRepository.save(product);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void delete(ProductRequest request) {
        productRepository.deleteById(this.validateDeleteProduct(request));
    }

    @Transactional(rollbackFor = {Exception.class})
    protected Product validateCreateProduct(ProductRequest request) {
        try {

            this.checkedRequireValues(request);

            brandRepository.findById(request.getBrandId());

            categoryRepository.findById(request.getCategoryId());

            Product checkProduct = this.findByNameIgnoreCase(request.getName());

            if (checkProduct != null ) {
                throw new BaseException(ErrorCode.FAIL_CREATE_PRODUCT);
            }

            return Product.builder()
                    .id(request.getId())
                    .name(request.getName())
                    .brandId(request.getBrandId())
                    .categoryId(request.getCategoryId())
                    .price(request.getPrice())
                    .viewRank(request.getViewRank())
                    .createdDt(LocalDateTime.now())
                    .updatedDt(LocalDateTime.now())
                    .build();
        }catch (BaseException e) {
            throw new BaseException(e.getErrorCode());
        }catch (Exception e) {
            throw new BaseException(e.getMessage(), ErrorCode.FAIL_CREATE_PRODUCT);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    protected Long validateDeleteProduct(ProductRequest request) {
       try {
           if(request.getId() == null || request.getId() <= 0) {
               throw new BaseException(ErrorCode.REQUIRED_PRODUCT_ID);
           }

           Product product = productRepository.findById(request.getId());
           if(product == null || product.getId() <= 0) {
               throw new BaseException(ErrorCode.PRODUCT_NOT_FOUND);
           }

           return request.getId();
        }catch (BaseException e) {
            throw new BaseException(e.getErrorCode());
        }catch (Exception e) {
           throw new BaseException(e.getMessage(), ErrorCode.FAIL_DELETE_PRODUCT);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    protected void validateUpdateProduct(ProductRequest request) {

        try {
            if (request.getId() == null || request.getId() <= 0) {
                throw new BaseException(ErrorCode.REQUIRED_PRODUCT_ID);
            }

            this.checkedRequireValues(request);

            brandRepository.findById(request.getBrandId());

            categoryRepository.findById(request.getCategoryId());

            Product checkProduct = this.findByNameIgnoreCase(request.getName());

            if (checkProduct != null) {
                throw new BaseException(ErrorCode.DUPLICATE_PRODUCT_NAME);
            }
        }catch (BaseException e) {
            throw new BaseException(e.getErrorCode());
        }catch (Exception e) {
            throw new BaseException(e.getMessage(), ErrorCode.FAIL_MODIFY_PRODUCT);
        }
    }

    private void checkedRequireValues(ProductRequest request){

        if (request.getName() == null || request.getName().isEmpty()) {
            throw new BaseException(ErrorCode.REQUIRED_PRODUCT_NAME);
        }

        if (request.getBrandId() == null || request.getBrandId() <= 0) {
            throw new BaseException(ErrorCode.REQUIRED_BRAND_ID);
        }

        if (request.getCategoryId() == null || request.getCategoryId() <= 0) {
            throw new BaseException(ErrorCode.REQUIRED_CATEGORY_ID);
        }

        if (request.getPrice() == null || request.getPrice() <= 0) {
            throw new BaseException(ErrorCode.REQUIRED_PRODUCT_PRICE);
        }
    }
}
