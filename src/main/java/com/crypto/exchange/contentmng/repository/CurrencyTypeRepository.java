package com.crypto.exchange.contentmng.repository;

import com.crypto.exchange.contentmng.model.CurrencyType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyTypeRepository extends JpaRepository<CurrencyType,Long> {
}
