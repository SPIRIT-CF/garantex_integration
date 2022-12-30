package com.crypto.binance_integrator.dto;

import java.math.BigDecimal;

public record PriceDTO(
        BigDecimal price,
        BigDecimal volume,
        BigDecimal amount,
        BigDecimal factor,
        String type
        //{"price":"1379641.92","volume":"16.29769932","amount":"22484989.19","factor":"0.046","type":"limit"
) {
}
