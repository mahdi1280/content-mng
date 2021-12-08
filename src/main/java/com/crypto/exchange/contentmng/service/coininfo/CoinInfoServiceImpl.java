package com.crypto.exchange.contentmng.service.coininfo;


import com.crypto.exchange.contentmng.model.CoinInfo;
import com.crypto.exchange.contentmng.repository.CoinInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CoinInfoServiceImpl implements CoinInfoService {

    private final CoinInfoRepository coinInfoRepository;

    public CoinInfoServiceImpl(CoinInfoRepository coinInfoRepository) {
        this.coinInfoRepository = coinInfoRepository;
    }


    @Override
    @Transactional
    public Optional<CoinInfo> findById(long id) {
        return coinInfoRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(CoinInfo coinInfo) {
        coinInfoRepository.save(coinInfo);
    }

}
