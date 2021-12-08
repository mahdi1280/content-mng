package com.crypto.exchange.contentmng.service.link;

import com.crypto.exchange.contentmng.model.CoinInfo;
import com.crypto.exchange.contentmng.model.Link;

import java.util.Optional;

public interface LinkService {

    Optional<Link> findByCoinInfo(CoinInfo coinInfo);
}
