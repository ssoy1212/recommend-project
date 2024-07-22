package com.item.brand.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.item.brand.domain.Brand;
import com.item.common.enums.MessageCode;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrandResponse {
    private final String message;
    private final String code;
    private final Brand brand;

    public static BrandResponse from(String code, Brand brand) {
        return BrandResponse.builder()
                .code(code)
                .brand(brand)
                .message(MessageCode.getMessage(code))
                .build();
    }

    public static BrandResponse from(Brand brand) {
        return BrandResponse.builder()
                .brand(brand)
                .build();
    }
    public static BrandResponse fromMessage(String code) {
        return BrandResponse.builder()
                .code(code)
                .message(MessageCode.getMessage(code))
                .build();
    }
}
