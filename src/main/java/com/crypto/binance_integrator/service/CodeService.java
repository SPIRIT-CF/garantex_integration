package com.crypto.binance_integrator.service;

import com.crypto.binance_integrator.dto.GarantexMarketDTO;
import com.crypto.binance_integrator.entity.Code;
import com.crypto.binance_integrator.repository.CodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CodeService {
    private final CodeRepository codeRepository;

    public Code save(String codeName) {
        Code code = new Code();
        code.setName(codeName);
        return codeRepository.save(code);
    }

    public void saveAll(List<GarantexMarketDTO> allMarkets) {
        allMarkets.forEach(market -> {
            if (!codeRepository.existsByName(market.id()))
                codeRepository.save(new Code(market.id()));
        });
    }

    public Code get(String codeName) {
        return codeRepository.getByName(codeName).orElseGet(() -> save(codeName));
    }

    public List<Code> getAll() {
        return codeRepository.findAll();
    }
}
