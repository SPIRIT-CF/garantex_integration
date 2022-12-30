package com.crypto.binance_integrator.dto;

import java.io.Serializable;

public record GarantexMarketDTO (
        String id,
        String name,
        String ask_unit,
        String bid_unit,
        String min_askd,
        String min_bid,
        String maker_fee,
        String taker_fee
) implements Serializable {}
    //{"id":"btcrub","name":"BTC/RUB","ask_unit":"btc","bid_unit":"rub","min_ask":"0.00001","min_bid":"5.0","maker_fee":"0.002","taker_fee":"0.0025"}

