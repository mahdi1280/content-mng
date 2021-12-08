package com.crypto.exchange.contentmng.service.coin;

import com.crypto.exchange.contentmng.model.Currency;

import java.util.Optional;

public interface CoinService {

    Optional<Currency> findById(long coinId);

    Currency save(Currency currency);
}
