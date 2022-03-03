package com.cocafehyperchain.service.strategy;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.http.HttpStatus;
import cn.hyperchain.sdk.account.Account;
import cn.hyperchain.sdk.exception.RequestException;
import cn.hyperchain.sdk.request.Request;
import cn.hyperchain.sdk.response.ReceiptResponse;
import cn.hyperchain.sdk.response.TxHashResponse;
import cn.hyperchain.sdk.service.ContractService;
import cn.hyperchain.sdk.service.ServiceManager;
import cn.hyperchain.sdk.transaction.Transaction;
import com.alibaba.fastjson.JSONObject;
import com.cocafehyperchain.domain.MintRequest;
import com.cocafehyperchain.util.Result;
import com.cocafehyperchain.util.ResultUtil;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.redcave.property.business.PropertyBusiness;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author huangzihe
 * @date 2021/12/29 4:40 下午
 */
@Component("mint")
@Log4j
public class MintMethodStrategy extends InvokeMethod {

    @Override
    public Transaction prepareTx(String contract, Object param) throws RequestException {
        MintRequest mintRequest = (MintRequest) param;
        // 参数转换

        long[] ids = ArrayUtil.unWrap(mintRequest.getIds().toArray(new Long[]{}));
        String[] owners = mintRequest.getOwners().toArray(new String[]{});
        String[] metas = mintRequest.getMetas().toArray(new String[]{});
        PropertyBusiness propertyBusiness = propertyBusinessMap.get(contract);
        return propertyBusiness.emitProperty(ids , owners,  metas, account.getAddress());
    }

    @Override
    public Result decode(String contract, ReceiptResponse response) {
        PropertyBusiness propertyBusiness = propertyBusinessMap.get(contract);
//        String result = propertyBusiness.decodeResult(response.getRet(), String.class);
        String txHash = response.getTxHash();
        return ResultUtil.success(txHash);
    }
}
