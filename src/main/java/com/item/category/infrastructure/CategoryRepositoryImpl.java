package com.item.category.infrastructure;

import com.item.category.domain.Category;
import com.item.category.service.port.CategoryRepository;
import com.item.common.exception.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final CategoryJpaRepository jpaRepository;

    @Override
    public Category findByNameIgnoreCase(String name) {
        return jpaRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Category findById(Long id) {
        return jpaRepository.findById(id).orElseThrow(CategoryNotFoundException::new).toModel();
    }
}
