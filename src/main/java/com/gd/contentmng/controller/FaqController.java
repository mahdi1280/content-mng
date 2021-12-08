package com.gd.contentmng.controller;

import com.gd.contentmng.dto.request.FaqAddRequest;
import com.gd.contentmng.dto.request.FaqCoinAddRequest;
import com.gd.contentmng.dto.request.FaqModifyRequest;
import com.gd.contentmng.model.Coin;
import com.gd.contentmng.model.CoinInfo;
import com.gd.contentmng.model.Faq;
import com.gd.contentmng.service.coin.CoinService;
import com.gd.contentmng.service.coininfo.CoinInfoService;
import com.gd.contentmng.service.faq.FaqService;
import com.gd.core.ErrorMessage;
import com.gd.core.RuleException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/faqs")
public class FaqController {

    private final FaqService faqService;
    private final CoinInfoService coinInfoService;
    private final MessageSourceAccessor messageSourceAccessor;
    private final CoinService coinService;

    public FaqController(FaqService faqService, CoinInfoService coinInfoService, MessageSourceAccessor messageSourceAccessor, CoinService coinService) {
        this.faqService = faqService;
        this.coinInfoService = coinInfoService;
        this.messageSourceAccessor = messageSourceAccessor;
        this.coinService = coinService;
    }

    @PostMapping
    public ResponseEntity<Object> saveFaq(@Valid @RequestBody FaqCoinAddRequest faqCoinAddRequest) {
        Coin coin = coinService.findByCode(faqCoinAddRequest.getCoinId()).orElseThrow(
                () -> new RuleException(ErrorMessage.error(messageSourceAccessor.getMessage("save.faq.find.coin"), "save.faq.find.coin")));
        CoinInfo coinInfo = coinInfoService.findById(coin.getId()).orElseThrow(
                () -> new RuleException(ErrorMessage.error(messageSourceAccessor.getMessage("save.faq.find.coin"), "save.faq.find.coin")));
        Collection<Faq> faqs = addFaq(faqCoinAddRequest, coinInfo);
        faqService.saveFaq(faqs);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Object> modifyFaq(@Valid @RequestBody FaqModifyRequest faqModifyRequest) {
        Faq faq = faqService.findById(faqModifyRequest.getId()).orElseThrow(
                () -> new RuleException(ErrorMessage.error(messageSourceAccessor.getMessage("faq.modify.find"), "faq.modify.find")));
        faqService.modifyFaq(addModifyFaq(faq, faqModifyRequest));
        return ResponseEntity.ok().build();
    }

    private Collection<Faq> addFaq(FaqCoinAddRequest faqCoinAddRequest, CoinInfo coinInfo) {

        List<Faq> faqs = new ArrayList<>();

        for (FaqAddRequest faqAddRequests : faqCoinAddRequest.getFaqs()) {
            Faq faq = Faq.builder()
                    .coinInfo(coinInfo)
                    .answer(faqAddRequests.getAnswer())
                    .question(faqAddRequests.getQuestion())
                    .build();
            faqs.add(faq);
        }
        return faqs;
    }

    private Faq addModifyFaq(Faq faq, FaqModifyRequest faqModifyRequest) {
        faq.setQuestion(faqModifyRequest.getQuestion());
        faq.setAnswer(faqModifyRequest.getAnswer());
        return faq;
    }
}
