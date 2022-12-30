package com.crypto.binance_integrator.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@AllArgsConstructor
public class BinanceRestConfig {

    //private final SettingsProvider settingsProvider;

/*    @Bean
    public BinanceApiRestClient binanceApiRestClient() {
        return new BinanceApiRestClientImpl(settingsProvider.getApiKey(), settingsProvider.getSecretKey());
    }*/

    @Bean
    public WebClient webClient() {
        return WebClient.create("https://garantex.io/api/v2");
    }

}
