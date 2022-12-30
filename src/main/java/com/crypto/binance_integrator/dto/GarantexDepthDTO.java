package com.crypto.binance_integrator.dto;

import java.util.List;

public record GarantexDepthDTO(
        Long milliseconds,
        List<PriceDTO> asks,
        List<PriceDTO> bids
) {}
