package com.item.product.controller;

import com.item.product.controller.port.ProductService;
import com.item.product.controller.response.ProductResponse;
import com.item.product.domain.ProductRequest;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/products")
@Builder
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/find/{id}")
    public ResponseEntity<ProductResponse> findProductList(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(ProductResponse.from("P1004",productService.getProductById(id)));
    }
    @PutMapping
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest request) {
        return ResponseEntity
                .ok()
                .body(ProductResponse.from("P1002",productService.update(request)));
    }
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        return ResponseEntity
                .ok()
                .body(ProductResponse.from("P1001",productService.create(request)));
    }
    @DeleteMapping
    public ResponseEntity<ProductResponse> deleteProduct(@RequestBody ProductRequest request) {
        productService.delete(request);
        return ResponseEntity
                .ok()
                .body(ProductResponse.fromMessage("P1003"));
    }
}
