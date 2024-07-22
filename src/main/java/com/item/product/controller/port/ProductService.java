package com.item.product.controller.port;

import com.item.product.domain.Product;
import com.item.product.domain.ProductRequest;


public interface ProductService {
    Product getProductById(Long id);
    Product findByNameIgnoreCase(String name);
    Product create(ProductRequest request);
    Product update(ProductRequest request);
    void delete(ProductRequest request);
}
