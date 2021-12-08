package com.crypto.exchange.contentmng.repository;

import com.crypto.exchange.contentmng.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Currency, Long> {

}
