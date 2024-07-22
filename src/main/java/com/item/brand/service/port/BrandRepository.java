package com.item.brand.service.port;

import com.item.brand.domain.Brand;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository {

    Brand findById(Long id);
    
    Brand save(Brand brand);

    List<Brand> findByNameLike(String name);

    Brand findByNameIgnoreCase(String name);

    void deleteById(Brand deleteBrand);
}
