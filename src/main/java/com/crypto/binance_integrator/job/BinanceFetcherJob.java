package com.crypto.binance_integrator.job;


import com.crypto.binance_integrator.dto.GarantexDepthDTO;
import com.crypto.binance_integrator.dto.GarantexMarketDTO;
import com.crypto.binance_integrator.entity.Code;
import com.crypto.binance_integrator.service.CodeService;
import com.crypto.binance_integrator.service.GarantexPricesFetcher;
import com.crypto.binance_integrator.service.PriceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
@EnableScheduling
public class BinanceFetcherJob {

    private final GarantexPricesFetcher garantexPricesFetcher;
    private final CodeService codeService;
    private final PriceService priceService;

    @Scheduled(fixedRate = 20000)
    public void getMarkets() {
        List<GarantexMarketDTO> allMarkets = garantexPricesFetcher.getAllMarkets();
        codeService.saveAll(allMarkets);
    }

    @Scheduled(fixedRate = 20000)
    public void getPrices() {
        List<Code> codesToGetPrices = codeService.getAll();
        Map<String, GarantexDepthDTO> depthsByMarketsMap = garantexPricesFetcher.getDepthsByMarketsMap(codesToGetPrices.stream()
                .map(Code::getName)
                .collect(Collectors.toSet()));

        codesToGetPrices.forEach(code -> {
            GarantexDepthDTO garantexDepthDTO = depthsByMarketsMap.get(code.getName());
            priceService.save(code,
                    garantexDepthDTO.asks().get(0).price().toBigInteger(),
                    garantexDepthDTO.bids().get(0).price().toBigInteger(),
                    LocalDateTime.now()
            );
        });
    }



/*    @Scheduled(fixedRate = 30000)
    private void call() {
        List<GarantexDepthDTO> depthDTOs = garantexPricesFetcher.getAllDepths()

        tickerPrices.forEach(tickerPrice -> {
            String codeName = null;
            BigDecimal price = null;
            try {
                codeName = tickerPrice.getSymbol();
                Code code = codeService.get(codeName);
                price = new BigDecimal(tickerPrice.getPrice());

                priceService.save(code, price.multiply(multiply).toBigInteger(), LocalDateTime.now());
            } catch (Exception e) {
                log.error("code {}, price {}, exc {}", codeName, price, e );
            }
        });
    }*/
}
