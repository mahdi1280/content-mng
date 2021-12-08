package com.gd.contentmng.service.faq;

import com.gd.contentmng.model.Faq;

import java.util.Collection;
import java.util.Optional;


public interface FaqService {

    void saveFaq(Collection<Faq> listFaq);

    Optional<Faq> findById(int id);

    void modifyFaq(Faq faq);
}
