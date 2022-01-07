package com.cocafehyperchain.controller;

import cn.hyperchain.sdk.account.Algo;
import com.cocafehyperchain.util.Result;
import com.cocafehyperchain.util.ResultUtil;
import com.redcave.property.business.AccountBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangzihe
 * @date 2022/1/6 4:23 下午
 */
@RestController
@RequestMapping("v1/")
public class AccountController {
    @Autowired
    AccountBusiness accountBusiness;

    @GetMapping("genAccount")
    public Result generateAccount() {
        return ResultUtil.success(accountBusiness.genAccount(Algo.SMRAW));
    }
}
