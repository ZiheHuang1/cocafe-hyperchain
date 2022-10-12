package com.cocafehyperchain.service;

import cn.hutool.http.HttpStatus;
import cn.hyperchain.sdk.account.Account;
import cn.hyperchain.sdk.exception.RequestException;
import cn.hyperchain.sdk.request.Request;
import cn.hyperchain.sdk.response.ReceiptResponse;
import cn.hyperchain.sdk.response.TxHashResponse;
import cn.hyperchain.sdk.service.ContractService;
import cn.hyperchain.sdk.transaction.Transaction;
import com.alibaba.fastjson.JSONObject;
import com.cocafehyperchain.domain.BaseRequest;
import com.cocafehyperchain.service.strategy.InvokeMethod;
import com.cocafehyperchain.util.Result;
import com.cocafehyperchain.util.ResultUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author huangzihe
 * @date 2021/12/30 11:05 上午
 */
@Component
@Log4j
public class InvokeMethodContext {
    @Autowired
    Map<String, InvokeMethod> strategyMap;

    @Autowired
    Account account;

    @Autowired
    ContractService contractService;

    public static String packagePath = "com.cocafehyperchain.domain.";

    /**
     * 模板方法
     * @param request
     * @return
     */
    public Result invokeMethod(BaseRequest request) {
        Result result;
        // 1、 获取对应的策略
        InvokeMethod method = strategyMap.get(request.getMethod());
        // 2、 获取对应的解析Request
        Class<?> aclass;
        try {
            // 3、 构造transaction
            aclass = Class.forName(packagePath + captureName(request.getMethod()) + "Request");
            Object object = JSONObject.parseObject(request.getParams(), aclass);
            Transaction tx = method.prepareTx(request.getContract(), object);
            // 4、 发送请求
            ReceiptResponse response = sendRequestToChain(tx);
            // 5、 解析结果
            result = method.decode(request.getContract(), response);
        } catch (ClassNotFoundException e) {
            log.debug(e.toString());
            return ResultUtil.error(HttpStatus.HTTP_BAD_REQUEST, "不支持的invoke方法");
        } catch (RequestException e) {
            log.debug(e.toString());
            return ResultUtil.error(HttpStatus.HTTP_BAD_REQUEST, e.getMsg());
        } catch (NumberFormatException e) {
            log.debug(e.getMessage());
            return ResultUtil.error(HttpStatus.HTTP_BAD_REQUEST, "请检查id范围");
        }
        log.debug("方法调用成功: " + JSONObject.toJSONString(request));
        log.debug("返回结果:" + JSONObject.toJSONString(result));
        return result;
    }

    /**
     * 向链上发送请求
     * @param tx
     * @return
     * @throws RequestException
     */
    private ReceiptResponse sendRequestToChain(Transaction tx) throws RequestException {
        tx.sign(account);
        Request<TxHashResponse> contractRequest = contractService.invoke(tx);
        return contractRequest.send().polling();
    }

    /**
     * 将字符串的首字母转大写
     * @param str 需要转换的字符串
     * @return
     */
    private static String captureName(String str) {
        // 进行字母的ascii编码前移，效率要高于截取字符串进行转换的操作
        char[] cs=str.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }
}
