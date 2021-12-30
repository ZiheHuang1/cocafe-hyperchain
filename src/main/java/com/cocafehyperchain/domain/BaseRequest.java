package com.cocafehyperchain.domain;

import lombok.Data;

/**
 * @author huangzihe
 * @date 2021/12/29 4:30 下午
 */
@Data
public class BaseRequest {
    String method;

    /**
     * 后续转为具体方法的参数
     */
    String params;
}
