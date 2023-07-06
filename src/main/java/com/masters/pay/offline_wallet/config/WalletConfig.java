package com.masters.pay.offline_wallet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WalletConfig {

    @Bean
    public WebClient.Builder getWebClientBuilder(){
      return WebClient.builder();
    }
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
