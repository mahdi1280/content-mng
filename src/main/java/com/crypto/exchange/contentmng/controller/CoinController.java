package com.crypto.exchange.contentmng.controller;

import com.crypto.exchange.contentmng.dto.request.CoinRequest;
import com.crypto.exchange.contentmng.dto.response.CoinResponse;
import com.crypto.exchange.contentmng.model.Currency;
import com.crypto.exchange.contentmng.model.CurrencyType;
import com.crypto.exchange.contentmng.service.coin.CoinService;
import com.crypto.exchange.contentmng.service.currencytype.CurrencyTypeService;
import com.gd.core.ErrorMessage;
import com.gd.core.RuleException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/coins")
public class CoinController {

    private static final Set<String> SUFFIX = new HashSet<>(Collections.singletonList("png"));
    private final CoinService coinService;
    private final CurrencyTypeService currencyTypeService;

    public CoinController(CoinService coinService, CurrencyTypeService currencyTypeService) {
        this.coinService = coinService;
        this.currencyTypeService = currencyTypeService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole({'ADMIN'})")
    public ResponseEntity<Object> save(@Valid @ModelAttribute CoinRequest coinRequest) {
        CurrencyType currencyType = currencyTypeService.findById(coinRequest.getCurrencyTypeId()).orElseThrow(() ->
                new RuleException(ErrorMessage.error("find.currency.type.not.found", "find.currency.type.not.found")));
        checkSuffix(coinRequest.getLogo());
        Currency currency = setCoin(coinRequest,currencyType);
        Currency currencyResponse = coinService.save(currency);
        return ResponseEntity.ok(new CoinResponse(currencyResponse.getId()));
    }

    @PutMapping("/{id:\\d*}/{enabled:(?:true|false)}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object> changeEnabled(@PathVariable long id,@PathVariable boolean enabled){
        Currency currency = coinService.findById(id).orElseThrow(() ->
                new RuleException(ErrorMessage.error("change.enabled.not.find", "change.enabled.not.find")));
        currency.setEnabled(enabled);
        coinService.save(currency);
        return ResponseEntity.ok().build();
    }

    private void checkSuffix(MultipartFile logo) {
        if (logo.getContentType() == null) {
            throw new RuleException(ErrorMessage.error("logo.content.type.null", "logo.content.type.null"));
        } else if (logo.getContentType().split("/").length <= 1) {
            throw new RuleException(ErrorMessage.error("logo.content.type", "logo.content.type"));
        } else if (!SUFFIX.contains(logo.getContentType().split("/")[1])) {
            throw new RuleException(ErrorMessage.error("logo.content.type.not.png", "logo.content.type.not.png"));
        }
    }

    private Currency setCoin(CoinRequest coinRequest,CurrencyType currencyType) {
        try {
            return Currency.builder()
                    .setLogo(coinRequest.getLogo().getBytes())
                    .setCode(coinRequest.getTitle())
                    .setTitle(coinRequest.getTitle())
                    .setName(coinRequest.getName())
                    .setCurrencyType(currencyType)
                    .build();
        } catch (IOException e) {
            throw new RuleException(ErrorMessage.error("upload.logo.fail", "upload.logo.fail"));
        }
    }
}
