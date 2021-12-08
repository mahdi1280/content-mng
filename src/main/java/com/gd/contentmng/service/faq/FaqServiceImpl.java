package com.gd.contentmng.service.faq;

import com.gd.contentmng.model.Faq;
import com.gd.contentmng.repository.FaqRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class FaqServiceImpl implements FaqService{

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
    public Optional<Faq> findById(int id) {
       return faqRepository.findById(id);
    }

    @Override
    @Transactional
    public void modifyFaq(Faq faq) {
        faqRepository.save(faq);
    }
}
