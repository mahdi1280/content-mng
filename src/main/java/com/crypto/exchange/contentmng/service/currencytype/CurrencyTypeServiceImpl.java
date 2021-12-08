package com.crypto.exchange.contentmng.service.currencytype;

import com.crypto.exchange.contentmng.model.CurrencyType;
import com.crypto.exchange.contentmng.repository.CurrencyTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrencyTypeServiceImpl implements CurrencyTypeService{

    private final CurrencyTypeRepository currencyTypeRepository;

    public CurrencyTypeServiceImpl(CurrencyTypeRepository currencyTypeRepository) {
        this.currencyTypeRepository = currencyTypeRepository;
    }

    @Override
    public Optional<CurrencyType> findById(long id) {
        return currencyTypeRepository.findById(id);
    }
}
