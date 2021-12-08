package com.crypto.exchange.contentmng.controller;

import com.crypto.exchange.contentmng.dto.request.FaqAddRequest;
import com.crypto.exchange.contentmng.dto.request.FaqCoinAddRequest;
import com.crypto.exchange.contentmng.dto.request.FaqModifyRequest;
import com.crypto.exchange.contentmng.dto.response.FaqResponse;
import com.crypto.exchange.contentmng.model.Currency;
import com.crypto.exchange.contentmng.model.CoinInfo;
import com.crypto.exchange.contentmng.model.Faq;
import com.crypto.exchange.contentmng.service.coin.CoinService;
import com.crypto.exchange.contentmng.service.coininfo.CoinInfoService;
import com.crypto.exchange.contentmng.service.faq.FaqService;
import com.gd.core.ErrorMessage;
import com.gd.core.RuleException;
import org.springframework.http.ResponseEntity;
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
    private final CoinService coinService;

    public FaqController(FaqService faqService, CoinInfoService coinInfoService, CoinService coinService) {
        this.faqService = faqService;
        this.coinInfoService = coinInfoService;
        this.coinService = coinService;
    }

    @PostMapping
    public ResponseEntity<Object> saveFaq(@Valid @RequestBody FaqCoinAddRequest faqCoinAddRequest) {
        Currency currency = coinService.findById(faqCoinAddRequest.getCoinId()).orElseThrow(
                () -> new RuleException(ErrorMessage.error("save.faq.find.coin", "save.faq.find.coin")));
        CoinInfo coinInfo = coinInfoService.findById(currency.getId()).orElseThrow(
                () -> new RuleException(ErrorMessage.error("save.faq.find.coin", "save.faq.find.coin")));
        Collection<Faq> faqs = addFaq(faqCoinAddRequest, coinInfo);
        faqService.saveFaq(faqs);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Object> modifyFaq(@Valid @RequestBody FaqModifyRequest faqModifyRequest) {
        Faq faq = faqService.findById(faqModifyRequest.getId()).orElseThrow(
                () -> new RuleException(ErrorMessage.error("faq.modify.find", "faq.modify.find")));
        faqService.modifyFaq(addModifyFaq(faq, faqModifyRequest));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id:\\d}")
    public ResponseEntity<List<FaqResponse>> getFaq(@PathVariable long id) {
        CoinInfo coinInfo = coinInfoService.findById(id)
                .orElseThrow(() -> new RuleException(ErrorMessage.error("faq.find.coin.info", "faq.find.coin.info")));
        List<Faq> faqs = faqService.findByCoinInfo(coinInfo);
        List<FaqResponse> faqResponse = new ArrayList<>();
        for (Faq faq : faqs) {
            faqResponse.add(new FaqResponse(faq.getId(), faq.getQuestion(), faq.getAnswer()));
        }
        return ResponseEntity.ok(faqResponse);
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
