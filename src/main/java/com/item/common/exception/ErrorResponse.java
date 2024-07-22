package com.item.common.exception;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private String message;
    private Integer code;

    private ErrorResponse(final ErrorCode code) {
        this.code = code.getStatus();
        this.message = code.getMessage();
    }

    public ErrorResponse(final String message) {
        this.message = message;
    }

    public static ErrorResponse of(final ErrorCode code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse of( final String message) {
        return new ErrorResponse(message);
    }
}