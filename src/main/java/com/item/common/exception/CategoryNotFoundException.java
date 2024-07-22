package com.item.common.exception;

public class CategoryNotFoundException extends BaseException {
    public CategoryNotFoundException() {
        super(ErrorCode.CATEGORY_NOT_FOUND);
    }
}
