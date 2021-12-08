package com.gd.contentmng.service.meta;

import com.gd.contentmng.model.CoinInfo;
import com.gd.contentmng.model.Meta;

import java.util.Collection;
import java.util.Optional;

public interface MetaService {

    CoinInfo saveAll(Collection<Meta> metas);

    void save(Meta meta);

    Optional<Meta> findById(int id);

    void deleteMeta(Meta meta);
}
