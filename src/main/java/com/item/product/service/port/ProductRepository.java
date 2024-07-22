package com.item.product.service.port;

import com.item.product.domain.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {
    Product save(Product product);

    Product findById(Long id);

    Product findByNameIgnoreCase(String name);

    void deleteById(Long id);
}
