package com.cocafehyperchain.service;

import com.cocafehyperchain.domain.BaseRequest;
import com.cocafehyperchain.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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