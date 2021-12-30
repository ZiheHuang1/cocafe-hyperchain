package com.cocafehyperchain.domain;

import lombok.Data;

import java.util.List;

/**
 * @author huangzihe
 * @date 2021/12/30 2:33 下午
 */
@Data
public class SetPropertyStatusRequest {
    List<Long> ids;

    List<Integer> status;

}
