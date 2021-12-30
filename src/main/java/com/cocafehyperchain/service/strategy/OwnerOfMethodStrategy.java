package com.cocafehyperchain.service.strategy;

import cn.hutool.http.HttpStatus;
import cn.hyperchain.sdk.account.Account;
import cn.hyperchain.sdk.exception.RequestException;
import cn.hyperchain.sdk.response.ReceiptResponse;
import cn.hyperchain.sdk.transaction.Transaction;
import com.alibaba.fastjson.JSONObject;
import com.cocafehyperchain.domain.MintRequest;
import com.cocafehyperchain.domain.OwnerOfRequest;
import com.cocafehyperchain.util.Result;
import com.cocafehyperchain.util.ResultUtil;
import com.redcave.property.business.PropertyBusiness;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author huangzihe
 * @date 2021/12/30 11:00 上午
 */
@Component("ownerOf")
@Log4j
public class OwnerOfMethodStrategy extends InvokeMethod{
    @Override
    public Transaction prepareTx(Object param) throws RequestException {
        OwnerOfRequest ownerOfRequest = (OwnerOfRequest) param;
        return propertyBusiness.ownerOf(ownerOfRequest.getId(), account);
    }

    @Override
    public Result decode(ReceiptResponse response) {
        String result = propertyBusiness.decodeResult(response.getRet(), String.class);
        return ResultUtil.success(result);
    }
}
