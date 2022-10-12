package com.cocafehyperchain;

import cn.hyperchain.sdk.service.impl.AccountServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import cn.hyperchain.sdk.service.AccountService;
import cn.hyperchain.sdk.service.impl.AccountServiceImpl;
import cn.hyperchain.sdk.provider.ProviderManager;

@Configuration
public class JarBeanConfiguration {
    @Bean
    public AccountService demo() {
        return new AccountServiceImpl(ProviderManager.emptyManager());  //该对象为Jar包对象
    }
}