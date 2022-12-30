package com.crypto.binance_integrator.service;

import com.crypto.binance_integrator.dto.GarantexDepthDTO;
import com.crypto.binance_integrator.dto.GarantexMarketDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class GarantexPricesFetcher {

    private final WebClient webClient;

    public List<GarantexMarketDTO> getAllMarkets() {
        Mono<List<GarantexMarketDTO>> response = webClient.get()
                .uri("/markets")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
        return response.block();
    }

    public Map<String, GarantexDepthDTO> getDepthsByMarketsMap(Set<String> marketIds) {
        Map<String, GarantexDepthDTO> marketAndDepthMap = new HashMap<>();
        for (String marketId : marketIds) {
            Mono<GarantexDepthDTO> response = webClient.get()
                    .uri(builder -> builder.path("/depth").queryParam("market", marketId).build())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(GarantexDepthDTO.class);
            marketAndDepthMap.put(marketId, response.block());
        }
        return marketAndDepthMap;
    }
}
