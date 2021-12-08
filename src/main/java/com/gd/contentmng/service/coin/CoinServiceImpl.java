package com.gd.contentmng.service.coin;

import com.gd.contentmng.model.Coin;
import com.gd.contentmng.repository.CoinRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoinServiceImpl implements CoinService {

    private final CoinRepository coinRepository;

    public CoinServiceImpl(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    @Override
    public Optional<Coin> findByCode(int coinId) {
        return coinRepository.findById(coinId);
    }
}
