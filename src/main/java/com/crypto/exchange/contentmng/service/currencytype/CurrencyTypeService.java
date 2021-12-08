package com.crypto.exchange.contentmng.service.currencytype;

import com.crypto.exchange.contentmng.model.CurrencyType;

import java.util.Optional;

public interface CurrencyTypeService {

    Optional<CurrencyType> findById(long id);
}
