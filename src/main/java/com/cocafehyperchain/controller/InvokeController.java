package com.cocafehyperchain.controller;

import com.cocafehyperchain.domain.BaseRequest;
import com.cocafehyperchain.service.ChainAccountService;
import com.cocafehyperchain.service.InvokeService;
import com.cocafehyperchain.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.*;

/**
 * @author huangzihe
 * @date 2021/12/29 4:28 下午
 */
@RestController
@RequestMapping("v1/")
public class InvokeController {
    @Autowired
    InvokeService invokeService;

    @Autowired
    ChainAccountService chainAccountService;

    @PostMapping("invoke")
    public Result invoke(@RequestBody BaseRequest request) {
        System.out.println("invoke start=================");
        return invokeService.invoke(request);
    }

    @PostMapping("generateUserInfo")
    public Result<String> generateUserInfo(String userId) {
        return chainAccountService.generateUserInfo();
    }

}
