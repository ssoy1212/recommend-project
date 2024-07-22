package com.item.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST.value(), "올바르지 않은 입력값입니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), "잘못된 HTTP 메서드를 호출했습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버 에러가 발생했습니다."),

    BRAND_NOT_FOUND(HttpStatus.NOT_FOUND.value(),  "해당 브랜드를 찾을 수 없습니다."),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND.value(),  "해당 카테고리를 찾을 수 없습니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND.value(),  "해당 상품을 찾을 수 없습니다."),
    RECOMMEND_NOT_FOUND(HttpStatus.NOT_FOUND.value(),  "추천 상품을 찾을 수 없습니다."),

    REQUIRED_CATEGORY_ID(HttpStatus.BAD_REQUEST.value(),"카테고리 아이디는 필수입니다."),
    REQUIRED_CATEGORY_NAME(HttpStatus.BAD_REQUEST.value(),"카테고리명은 필수입니다."),

    REQUIRED_BRAND_ID(HttpStatus.BAD_REQUEST.value(),"브랜드 아이디는 필수입니다."),
    REQUIRED_BRAND_NAME(HttpStatus.BAD_REQUEST.value(),"브랜드명은 필수입니다"),
    REQUIRED_BRAND_VIEWRANK(HttpStatus.BAD_REQUEST.value(),"브랜드 노출순위는 필수입니다"),

    REQUIRED_PRODUCT_ID(HttpStatus.BAD_REQUEST.value(),"상품 아이디는 필수입니다."),
    REQUIRED_PRODUCT_NAME(HttpStatus.BAD_REQUEST.value(),"상품명은 필수입니다."),
    REQUIRED_PRODUCT_PRICE(HttpStatus.BAD_REQUEST.value(),"상품가격을 확인해주세요"),

    DUPLICATE_BRAND_NAME(HttpStatus.BAD_REQUEST.value(),"이미 등록된 브랜드명입니다."),
    DUPLICATE_PRODUCT_NAME(HttpStatus.BAD_REQUEST.value(),"이미 등록된 상품명입니다."),

    FAIL_CREATE_BRAND(HttpStatus.INTERNAL_SERVER_ERROR.value(),"브랜드 등록이 실패했습니다."),
    FAIL_CREATE_PRODUCT(HttpStatus.INTERNAL_SERVER_ERROR.value(),"상품 등록이 실패했습니다."),
    FAIL_DELETE_BRAND(HttpStatus.INTERNAL_SERVER_ERROR.value(),"브랜드 삭제에 실패했습니다."),

    FAIL_MODIFY_BRAND(HttpStatus.INTERNAL_SERVER_ERROR.value(),"브랜드 변경이 실패했습니다.")
    ;
    private final String message;
    private final Integer status;

    ErrorCode(final Integer status, final String message) {
        this.status = status;
        this.message = message;
    }
}