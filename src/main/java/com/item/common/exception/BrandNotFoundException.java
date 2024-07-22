package com.item.common.exception;

public class BrandNotFoundException extends BaseException {
    public BrandNotFoundException() {
        super(ErrorCode.BRAND_NOT_FOUND);
    }
}
