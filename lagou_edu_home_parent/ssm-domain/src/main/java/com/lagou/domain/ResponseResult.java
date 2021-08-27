package com.lagou.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应结果封装对象
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult {

    private Boolean success;
    private Integer state;
    private String message;
    private Object content;

}
