package com.item.common.exception;

public class ProductNotFoundException extends BaseException {

    public ProductNotFoundException() {
        super(ErrorCode.PRODUCT_NOT_FOUND);
    }
}
