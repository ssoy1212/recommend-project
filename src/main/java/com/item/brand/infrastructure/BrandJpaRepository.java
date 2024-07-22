package com.item.brand.infrastructure;

import com.item.brand.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BrandJpaRepository extends JpaRepository<BrandEntity, Long> {

    Optional<List<Brand>> findByNameContainsIgnoreCase(String name);

    Brand findByNameIgnoreCase(String name);
}
