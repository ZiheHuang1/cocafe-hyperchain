package com.cocafehyperchain.service.strategy;

import cn.hutool.core.util.ArrayUtil;
import cn.hyperchain.sdk.exception.RequestException;
import cn.hyperchain.sdk.response.ReceiptResponse;
import cn.hyperchain.sdk.transaction.Transaction;
import com.cocafehyperchain.domain.SetPropertyStatusRequest;
import com.cocafehyperchain.domain.TransferRequest;
import com.cocafehyperchain.util.Result;
import com.cocafehyperchain.util.ResultUtil;
import com.redcave.property.business.PropertyBusiness;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author huangzihe
 * @date 2021/12/30 2:34 下午
 */
@Component("setPropertyStatus")
public class SetPropertyStatusMethodStrategy extends InvokeMethod {
    @Override
    public Transaction prepareTx(String contract, Object param) throws RequestException {
        SetPropertyStatusRequest request = (SetPropertyStatusRequest) param;
        long[] ids = ArrayUtil.unWrap(request.getIds().toArray(new Long[]{}));
        int[] status = ArrayUtil.unWrap(request.getStatus().toArray(new Integer[]{}));
        PropertyBusiness propertyBusiness = propertyBusinessMap.get(contract);
        return propertyBusiness.setPropertyStatus(ids, status, account,Transaction.DEFAULT_GAS_PRICE,Transaction.DEFAULT_GAS_LIMIT);
    }

    @Override
    public Result decode(String contract, ReceiptResponse response) {
        PropertyBusiness propertyBusiness = propertyBusinessMap.get(contract);
        String result = propertyBusiness.decodeResult(response.getRet(), String.class);
        return ResultUtil.success(result);
    }
}
