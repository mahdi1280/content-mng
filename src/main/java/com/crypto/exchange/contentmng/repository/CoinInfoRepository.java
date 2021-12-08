package com.crypto.exchange.contentmng.repository;

import com.crypto.exchange.contentmng.model.CoinInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinInfoRepository extends JpaRepository<CoinInfo, Long> {
}
