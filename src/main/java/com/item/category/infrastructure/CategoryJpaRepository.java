package com.item.category.infrastructure;

import com.item.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {
    Category findByNameIgnoreCase(String name);
}
