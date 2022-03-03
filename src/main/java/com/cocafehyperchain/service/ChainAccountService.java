package com.cocafehyperchain.service;

import cn.hyperchain.sdk.account.Account;
import cn.hyperchain.sdk.account.Algo;
import cn.hyperchain.sdk.service.AccountService;
import com.cocafehyperchain.util.Result;
import com.cocafehyperchain.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huangzihe
 * @date 2022/3/3 9:47 上午
 */
@Service
public class ChainAccountService {
    @Autowired
    AccountService accountService;

    public Result<String> generateUserInfo() {
        Account account = accountService.genAccount(Algo.SMRAW);
        String json = account.toJson();
        return ResultUtil.success(json);

    }

}
