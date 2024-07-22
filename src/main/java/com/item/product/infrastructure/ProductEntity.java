package com.item.product.infrastructure;

import com.item.common.entity.BaseEntity;
import com.item.product.domain.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "products")
public class ProductEntity extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "view_rank",nullable = false)
    private Long viewRank;

    @Column(name = "price")
    private Long price;

    @Transient
    private String brandName;

    @Transient
    private String categoryName;

    public static ProductEntity from(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.id  = product.getId();
        productEntity.viewRank = product.getViewRank();
        productEntity.name = product.getName();
        productEntity.brandId = product.getBrandId();
        productEntity.price = product.getPrice();
        productEntity.categoryId = product.getCategoryId();
        productEntity.brandName = product.getName();
        productEntity.categoryName = product.getName();
        productEntity.createdDt = product.getCreatedDt();
        productEntity.updatedDt = product.getUpdatedDt();
        return productEntity;
    }

    public Product toModel() {
        return Product.builder()
                .id(id)
                .name(name)
                .viewRank(viewRank)
                .brandId(brandId)
                .categoryId(categoryId)
                .price(price)
                .createdDt(createdDt)
                .updatedDt(updatedDt)
                .build();
    }

}
