package com.gd.contentmng.service.link;

import com.gd.contentmng.model.CoinInfo;
import com.gd.contentmng.model.Link;

import java.util.Optional;

public interface LinkService {

    Optional<Link> findByCoinInfo(CoinInfo coinInfo);
}
