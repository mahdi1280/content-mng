package com.crypto.exchange.contentmng.repository;

import com.crypto.exchange.contentmng.model.CoinInfo;
import com.crypto.exchange.contentmng.model.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaqRepository extends JpaRepository<Faq, Long> {

    List<Faq> findByCoinInfo(CoinInfo coinInfo);
}
