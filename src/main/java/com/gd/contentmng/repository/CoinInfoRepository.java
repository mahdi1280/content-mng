package com.gd.contentmng.repository;

import com.gd.contentmng.model.CoinInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinInfoRepository extends JpaRepository<CoinInfo, Long> {
}
