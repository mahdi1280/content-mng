package com.crypto.exchange.contentmng.service.faq;

import com.crypto.exchange.contentmng.model.CoinInfo;
import com.crypto.exchange.contentmng.model.Faq;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FaqService {

    void saveFaq(Collection<Faq> listFaq);

    Optional<Faq> findById(long id);

    void modifyFaq(Faq faq);

    List<Faq> findByCoinInfo(CoinInfo coinInfo);
}
