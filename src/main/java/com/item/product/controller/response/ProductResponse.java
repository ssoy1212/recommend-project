package com.item.product.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.item.common.enums.MessageCode;
import com.item.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
    private final String message;
    private final String code;
    private final Product product;
    private final List<Product> productList;

    public static ProductResponse from(String code, Product product) {
        return ProductResponse.builder()
                .code(code)
                .product(product)
                .message(MessageCode.getMessage(code))
                .build();
    }

    public static ProductResponse fromMessage(String code) {
        return ProductResponse.builder()
                .code(code)
                .message(MessageCode.getMessage(code))
                .build();
    }
}
