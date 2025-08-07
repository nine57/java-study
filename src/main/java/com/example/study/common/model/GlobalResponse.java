package com.example.study.common.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * Response Wrapper
 * </p>
 *
 * @author : 변영우 byw1666@wjcompass.com
 * @since : 2025-08-07
 */

@Getter
@Builder
@ToString
public class GlobalResponse<T> {
    private final String code;
    private final String message;
    private final int status;
    private final T data;

    public static GlobalResponse<Void> success() {
        return GlobalResponse.<Void>builder()
                .code("0000")
                .message("success")
                .status(HttpStatus.OK.value())
                .build();
    }

    public static <T> GlobalResponse<T> success(T data) {
        return GlobalResponse.<T>builder()
                .code("0000")
                .message("success")
                .status(HttpStatus.OK.value())
                .data(data)
                .build();
    }

    public static <T> GlobalResponse<T> fail(String message, HttpStatus status) {
        return GlobalResponse.<T>builder()
                .code("9999")
                .message(message)
                .status(status.value())
                .build();
    }
}
