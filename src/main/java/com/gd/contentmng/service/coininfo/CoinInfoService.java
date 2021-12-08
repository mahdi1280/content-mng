package com.gd.contentmng.service.coininfo;

import com.gd.contentmng.model.CoinInfo;

import java.util.Optional;

public interface CoinInfoService {

    Optional<CoinInfo> findById(long id);

    void save(CoinInfo coinInfo);

}
