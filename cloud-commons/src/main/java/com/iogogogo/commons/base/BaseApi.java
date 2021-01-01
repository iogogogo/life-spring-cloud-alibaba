package com.iogogogo.commons.base;

import com.iogogogo.commons.domain.ResponseWrapper;
import org.springframework.http.HttpStatus;

/**
 * Created by tao.zeng on 2021/1/1.
 */
public interface BaseApi {

    default ResponseWrapper<?> ok() {
        return ok(null);
    }

    default <T> ResponseWrapper<?> ok(T data) {
        return result(HttpStatus.OK, data);
    }

    default <T> ResponseWrapper<?> result(HttpStatus httpStatus, T data) {
        return result(httpStatus.value(), httpStatus.getReasonPhrase(), data);
    }

    default <T> ResponseWrapper<?> result(int code, String message, T data) {
        return ResponseWrapper.builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }
}
