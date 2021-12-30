package com.cocafehyperchain.domain;

import lombok.Data;

import java.util.List;

/**
 * @author huangzihe
 * @date 2021/12/30 2:19 下午
 */
@Data
public class TransferRequest {
    String from;

    List<String> tos;

    List<Long> ids;
}
