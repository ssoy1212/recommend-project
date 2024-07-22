package com.item.recommend.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RecommendEntity {
    @Id
    private Long id;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "brandName")
    private String brandName;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "total_price")
    private Long totalPrice;

    @Column(name = "price_type")
    private String priceType;

    @Column(name = "price")
    private Long price;

    public RecommendEntity( Long brandId, String brandName, Long categoryId, String categoryName, Long totalPrice, String priceType ,Long price) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.totalPrice = totalPrice;
        this.priceType = priceType;
        this.price = price;
    }

    public RecommendEntity() {

    }
}
