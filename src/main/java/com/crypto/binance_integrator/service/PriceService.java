package com.crypto.binance_integrator.service;

import com.crypto.binance_integrator.entity.Code;
import com.crypto.binance_integrator.entity.Price;
import com.crypto.binance_integrator.repository.PriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;

    public Optional<Price> getLastPrice(Code code) {
        return priceRepository.findFirstByCodeOrderByTimeDesc(code);
    }

    public void clear(LocalDateTime upTo) {
        priceRepository.deleteAllByTimeLessThan(upTo);
    }

    public Price save(Code code, BigInteger ask, BigInteger bid, LocalDateTime time) {
        Price price = new Price();
        price.setCode(code);
        price.setAsk(ask);
        price.setBid(bid);
        price.setTime(time);
        return priceRepository.save(price);
    }

}
