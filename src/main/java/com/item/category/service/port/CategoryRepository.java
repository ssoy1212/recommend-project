package com.item.category.service.port;

import com.item.category.domain.Category;

public interface CategoryRepository {
    Category findByNameIgnoreCase(String name);

    Category findById(Long id);
}
