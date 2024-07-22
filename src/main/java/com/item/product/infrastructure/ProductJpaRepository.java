package com.item.product.infrastructure;

import com.item.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    Product findByNameIgnoreCase(String name);
}
