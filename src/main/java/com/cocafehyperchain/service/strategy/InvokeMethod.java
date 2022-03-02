package com.cocafehyperchain.service.strategy;

import cn.hyperchain.sdk.account.Account;
import cn.hyperchain.sdk.exception.RequestException;
import cn.hyperchain.sdk.request.Request;
import cn.hyperchain.sdk.response.ReceiptResponse;
import cn.hyperchain.sdk.service.ContractService;
import cn.hyperchain.sdk.transaction.Transaction;
import com.cocafehyperchain.util.Result;
import com.redcave.property.business.PropertyBusiness;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public abstract class InvokeMethod {

    @Autowired
    HashMap<String, PropertyBusiness> propertyBusinessMap;

    @Autowired
    Account account;

    @Autowired
    ContractService contractService;

    public abstract Transaction prepareTx(String contract, Object param) throws RequestException;

    public abstract Result decode(String contract, ReceiptResponse response);
}
