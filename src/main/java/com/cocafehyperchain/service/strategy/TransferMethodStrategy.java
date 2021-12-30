package com.cocafehyperchain.service.strategy;

import cn.hutool.core.util.ArrayUtil;
import cn.hyperchain.sdk.exception.RequestException;
import cn.hyperchain.sdk.response.ReceiptResponse;
import cn.hyperchain.sdk.transaction.Transaction;
import com.cocafehyperchain.domain.TransferRequest;
import com.cocafehyperchain.util.Result;
import com.cocafehyperchain.util.ResultUtil;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author huangzihe
 * @date 2021/12/30 2:20 下午
 */
@Component("transfer")
public class TransferMethodStrategy extends InvokeMethod {


    @Override
    public Transaction prepareTx(Object param) throws RequestException {
        TransferRequest transferRequest = (TransferRequest) param;
        long[] ids = ArrayUtil.unWrap(transferRequest.getIds().toArray(new Long[]{}));
        String[] tos = transferRequest.getTos().toArray(new String[]{});
        return propertyBusiness.transferFrom(transferRequest.getFrom() , tos, ids, account);
    }

    @Override
    public Result decode(ReceiptResponse response) {
        String result = propertyBusiness.decodeResult(response.getRet(), String.class);
        return ResultUtil.success(result);
    }
}
