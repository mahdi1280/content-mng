package com.crypto.exchange.contentmng.service.meta;

import com.crypto.exchange.contentmng.model.CoinInfo;
import com.crypto.exchange.contentmng.model.Meta;

import java.util.Collection;
import java.util.Optional;

public interface MetaService {

    CoinInfo saveAll(Collection<Meta> metas);

    void save(Meta meta);

    Optional<Meta> findById(long id);

    void deleteMeta(Meta meta);

}
