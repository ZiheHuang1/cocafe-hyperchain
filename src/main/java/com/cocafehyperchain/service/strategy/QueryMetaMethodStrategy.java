package com.cocafehyperchain.service.strategy;

import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import cn.hyperchain.sdk.account.Account;
import cn.hyperchain.sdk.exception.RequestException;
import cn.hyperchain.sdk.request.Request;
import cn.hyperchain.sdk.response.ReceiptResponse;
import cn.hyperchain.sdk.response.TxHashResponse;
import cn.hyperchain.sdk.service.AccountService;
import cn.hyperchain.sdk.service.ContractService;
import cn.hyperchain.sdk.transaction.Transaction;
import com.alibaba.fastjson.JSONObject;
import com.cocafehyperchain.domain.MintRequest;
import com.cocafehyperchain.domain.QueryMetaRequest;
import com.cocafehyperchain.util.Result;
import com.cocafehyperchain.util.ResultUtil;
import com.redcave.property.business.AccountBusiness;
import com.redcave.property.business.PropertyBusiness;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author huangzihe
 * @date 2021/12/29 5:15 下午
 */
@Component("queryMeta")
@Log4j
public class QueryMetaMethodStrategy extends InvokeMethod {

    @Override
    public Transaction prepareTx(Object param) throws RequestException {
        QueryMetaRequest metaRequest = (QueryMetaRequest) param;
        return propertyBusiness.queryMeta(metaRequest.getId(), 1, account);
    }

    @Override
    public Result decode(ReceiptResponse response) {
        String[] result = propertyBusiness.decodeResult(response.getRet(), String[].class);
        return ResultUtil.success(result);
    }
}
