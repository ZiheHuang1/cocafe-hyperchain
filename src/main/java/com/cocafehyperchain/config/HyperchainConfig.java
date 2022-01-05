package com.cocafehyperchain.config;

import cn.hyperchain.sdk.account.Account;
import cn.hyperchain.sdk.provider.DefaultHttpProvider;
import cn.hyperchain.sdk.provider.HttpProvider;
import cn.hyperchain.sdk.provider.ProviderManager;
import cn.hyperchain.sdk.service.AccountService;
import cn.hyperchain.sdk.service.ContractService;
import cn.hyperchain.sdk.service.ServiceManager;
import com.redcave.property.business.AccountBusiness;
import com.redcave.property.business.PropertyBusiness;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangzihe
 * @date 2021/12/29 4:32 下午
 */
@Configuration
public class HyperchainConfig {
    @Value("${hyperchain.node}")
    String node;

    @Value("${hyperchain.accountJson}")
    String accountJson;

    @Value("${hyperchain.contractAddress}")
    String contractAddress;

    @Bean
    public ProviderManager providerManager() {
        //Todo 后续如果多节点，需要改造
        HttpProvider httpProvider = new DefaultHttpProvider.Builder()
                .setUrl(node)
                .build();
        return ProviderManager.createManager(httpProvider);
    }

    @Bean
    public Account account(ProviderManager providerManager) {
        AccountBusiness accountBusiness = new AccountBusiness(providerManager);
        return accountBusiness.accountFromJson(accountJson);
    }

    @Bean
    public PropertyBusiness propertyBusiness(ProviderManager providerManager) {
        return new PropertyBusiness(providerManager, contractAddress);
    }

    @Bean
    public ContractService contractService(ProviderManager providerManager) {
        return ServiceManager.getContractService(providerManager);
    }

    @Bean
    public AccountBusiness accountService(ProviderManager providerManager) {
        return new AccountBusiness(providerManager);
    }
}
