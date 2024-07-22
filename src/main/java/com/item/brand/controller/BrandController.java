package com.item.brand.controller;

import com.item.brand.controller.port.BrandService;
import com.item.brand.controller.response.BrandResponse;
import com.item.brand.domain.BrandRequest;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/brands")
@Builder
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @GetMapping("/find/{name}")
    public ResponseEntity<List<BrandResponse>> findBrandList(@PathVariable String name) {
        List<BrandResponse> brandList = brandService.findByNameLike(name).stream().map(BrandResponse::from)
                .collect(Collectors.toList());;
        return ResponseEntity
                .ok()
                .body(brandList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> brandDetail(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(BrandResponse.from("B1004",brandService.getById(id)));
    }

    @PutMapping
    public ResponseEntity<BrandResponse> updateBrand(@RequestBody BrandRequest request) {
        return ResponseEntity
                .ok()
                .body(BrandResponse.from("B1002",brandService.update(request)));
    }

    @PostMapping
    public ResponseEntity<BrandResponse> create(@RequestBody BrandRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BrandResponse.from("B1001",brandService.create(request)));
    }

    @DeleteMapping
    public ResponseEntity<BrandResponse> delete(@RequestBody BrandRequest request) {
        brandService.delete(request);
        return ResponseEntity.ok()
                .body((BrandResponse.fromMessage("B1003")));
    }
}
