package com.crypto.exchange.contentmng.service.coininfo;

import com.crypto.exchange.contentmng.model.CoinInfo;

import java.util.Optional;

public interface CoinInfoService {

    Optional<CoinInfo> findById(long id);

    void save(CoinInfo coinInfo);

}
