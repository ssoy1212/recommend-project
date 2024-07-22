package com.item.product.infrastructure;

import com.item.common.exception.ProductNotFoundException;
import com.item.product.domain.Product;
import com.item.product.service.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductJpaRepository jpaRepository;

    @Override
    public Product save(Product product) {
        return jpaRepository.save(ProductEntity.from(product)).toModel();
    }

    @Override
    public Product findById(Long id) {
       return jpaRepository.findById(id).orElseThrow(ProductNotFoundException::new).toModel();
    }

    @Override
    public Product findByNameIgnoreCase(String name) {
        return jpaRepository.findByNameIgnoreCase(name);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
