package com.cocafehyperchain.domain;

import lombok.Data;

import java.util.List;

/**
 * @author huangzihe
 * @date 2021/12/29 4:49 下午
 */
@Data
public class MintRequest {

    List<Long> ids;

    List<String> owners;

    List<String> metas;

}
