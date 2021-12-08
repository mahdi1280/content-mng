package com.crypto.exchange.contentmng.service.faq;

import com.crypto.exchange.contentmng.model.CoinInfo;
import com.crypto.exchange.contentmng.model.Faq;
import com.crypto.exchange.contentmng.repository.FaqRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class FaqServiceImpl implements FaqService {

    private final FaqRepository faqRepository;

    public FaqServiceImpl(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    @Override
    @Transactional
    public void saveFaq(Collection<Faq> listFaq) {
        faqRepository.saveAll(listFaq);
    }

    @Override
    public Optional<Faq> findById(long id) {
        return faqRepository.findById(id);
    }

    @Override
    @Transactional
    public void modifyFaq(Faq faq) {
        faqRepository.save(faq);
    }

    @Override
    public List<Faq> findByCoinInfo(CoinInfo coinInfo) {
        return faqRepository.findByCoinInfo(coinInfo);
    }

}
