package com.cocafehyperchain.service.strategy;

import cn.hyperchain.sdk.exception.RequestException;
import cn.hyperchain.sdk.response.ReceiptResponse;
import cn.hyperchain.sdk.transaction.Transaction;
import com.cocafehyperchain.domain.QueryStatusRequest;
import com.cocafehyperchain.util.Result;
import com.cocafehyperchain.util.ResultUtil;

/**
 * @author huangzihe
 * @date 2021/12/31 9:56 上午
 */
public class QueryStatusMethodStrategy extends InvokeMethod {
    @Override
    public Transaction prepareTx(Object param) throws RequestException {
        QueryStatusRequest request = (QueryStatusRequest) param;
        return propertyBusiness.queryStatus(request.getId(), account);
    }

    @Override
    public Result decode(ReceiptResponse response) {
        String result = propertyBusiness.decodeResult(response.getRet(), String.class);
        return ResultUtil.success(result);
    }
}
