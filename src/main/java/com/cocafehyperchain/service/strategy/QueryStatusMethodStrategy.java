package com.cocafehyperchain.service.strategy;

import cn.hyperchain.sdk.exception.RequestException;
import cn.hyperchain.sdk.response.ReceiptResponse;
import cn.hyperchain.sdk.transaction.Transaction;
import com.cocafehyperchain.domain.QueryStatusRequest;
import com.cocafehyperchain.util.Result;
import com.cocafehyperchain.util.ResultUtil;
import com.redcave.property.business.PropertyBusiness;
import org.springframework.stereotype.Component;

/**
 * @author huangzihe
 * @date 2021/12/31 9:56 上午
 */
@Component("queryStatus")
public class QueryStatusMethodStrategy extends InvokeMethod {
    @Override
    public Transaction prepareTx(String contract, Object param) throws RequestException {
        QueryStatusRequest request = (QueryStatusRequest) param;
        PropertyBusiness propertyBusiness = propertyBusinessMap.get(contract);
        return propertyBusiness.queryStatus(request.getId(), account);
    }

    @Override
    public Result decode(String contract, ReceiptResponse response) {
        PropertyBusiness propertyBusiness = propertyBusinessMap.get(contract);
        String result = propertyBusiness.decodeResult(response.getRet(), String.class);
        return ResultUtil.success(result);
    }
}
