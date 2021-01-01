package com.iogogogo.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by tao.zeng on 2021/1/1.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWrapper<T> implements Serializable {

    private int code;

    private String message;

    private T data;
}
