package com.cocafehyperchain.controller;

import cn.hyperchain.sdk.service.ContractService;
import com.cocafehyperchain.domain.BaseRequest;
import com.cocafehyperchain.service.InvokeService;
import com.cocafehyperchain.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangzihe
 * @date 2021/12/29 4:28 下午
 */
@RestController
@RequestMapping("v1/")
public class InvokeController {
    @Autowired
    InvokeService invokeService;

    @PostMapping("syncInvoke")
    public Result syncInvoke(@RequestBody BaseRequest request) {
        return invokeService.syncOnvoke(request);
    }

    @PostMapping("asyncInvoke")
    public Result asyncInvoke(@RequestBody BaseRequest request) {
        return invokeService.asyncOnvoke(request);
    }

}
