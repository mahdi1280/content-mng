package com.crypto.exchange.contentmng.service.coin;

import com.crypto.exchange.contentmng.model.Currency;
import com.crypto.exchange.contentmng.repository.CoinRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoinServiceImpl implements CoinService {

    private final CoinRepository coinRepository;

    public CoinServiceImpl(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    @Override
    public Optional<Currency> findById(long coinId) {
        return coinRepository.findById(coinId);
    }

    @Override
    public Currency save(Currency currency) {
        return coinRepository.save(currency);
    }
}
