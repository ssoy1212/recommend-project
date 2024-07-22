package com.item.brand.infrastructure;

import com.item.brand.domain.Brand;
import com.item.brand.service.port.BrandRepository;
import com.item.common.exception.BrandNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BrandRepositoryImpl implements BrandRepository {
    private final BrandJpaRepository jpaRepository;

    @Override
    public Brand findById(Long id) {
        return jpaRepository.findById(id).orElseThrow(BrandNotFoundException::new).toModel();
    }

    @Override
    public List<Brand> findByNameLike(String name) {
        return jpaRepository.findByNameContainsIgnoreCase(name)
                .orElseThrow(BrandNotFoundException::new);
    }

    @Override
    public Brand findByNameIgnoreCase(String name) {
        return jpaRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Brand save(Brand brand) {;
        return jpaRepository.save(BrandEntity.from(brand)).toModel();
    }

    @Override
    public void deleteById(Brand deleteBrand) {
        jpaRepository.deleteById(deleteBrand.getId());
    }
}
