package com.item.brand.controller.port;

import com.item.brand.domain.Brand;
import com.item.brand.domain.BrandRequest;

import java.util.List;

public interface BrandService {
    Brand getById(Long id);
    List<Brand> findByNameLike(String name);
    Brand create(BrandRequest request);
    Brand update(BrandRequest request);
    void delete(BrandRequest request);
}
