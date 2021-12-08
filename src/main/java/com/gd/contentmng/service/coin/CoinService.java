package com.gd.contentmng.service.coin;

import com.gd.contentmng.model.Coin;

import java.util.Optional;

public interface CoinService {

    Optional<Coin> findByCode(int coinId);
}
