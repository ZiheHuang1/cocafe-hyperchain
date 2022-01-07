package com.cocafehyperchain.service;

import cn.hutool.http.HttpStatus;
import cn.hyperchain.sdk.exception.RequestException;
import com.cocafehyperchain.domain.BaseRequest;
import com.cocafehyperchain.service.strategy.InvokeMethod;
import com.cocafehyperchain.service.strategy.MintMethodStrategy;
import com.cocafehyperchain.util.Result;
import com.cocafehyperchain.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author huangzihe
 * @date 2021/12/29 4:28 下午
 */
@Service
public class InvokeService {

    @Autowired
    InvokeMethodContext context;

    public Result invoke(BaseRequest request) {
        return context.invokeMethod(request);
    }

}