package com.item.common.exception;

public class RecommendNotFoundException extends BaseException {
    public RecommendNotFoundException() {
        super(ErrorCode.RECOMMEND_NOT_FOUND);
    }

}