package com.item.common.enums;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum MessageCode {
    CREATE_BRAND("B1001","브랜드 생성이 완료되었습니다."),
    UPDATE_BRAND("B1002","브랜드 변경이 완료되었습니다."),
    DELETE_BRAND("B1003","브랜드 삭제가 완료되었습니다."),
    GET_BRAND("B1004","브랜드 조회결과입니다."),

    CREATE_PRODUCT("P1001","상품 생성이 완료되었습니다."),
    UPDATE_PRODUCT("P1002","상품 변경이 완료되었습니다."),
    DELETE_PRODUCT("P1003","상품 삭제가 완료되었습니다."),
    GET_PRODUCT("P1004","상품 조회결과입니다.");

    private final String message;
    private final String code;

    MessageCode(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    private static final Map<String, String> MESSAGE_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(MessageCode::getCode, MessageCode::getMessage)));

   public static String getMessage(final String code) {
       return MESSAGE_MAP.get(code);
   }
}
