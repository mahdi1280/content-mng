package com.crypto.exchange.contentmng.controller;

import com.crypto.exchange.contentmng.dto.request.CoinInfoAddRequest;
import com.crypto.exchange.contentmng.dto.request.CoinInfoModifyRequest;
import com.crypto.exchange.contentmng.dto.response.CoinInfoAddResponse;
import com.crypto.exchange.contentmng.dto.response.CoinInfoGetResponse;
import com.crypto.exchange.contentmng.dto.response.LinkResponse;
import com.crypto.exchange.contentmng.model.*;
import com.crypto.exchange.contentmng.service.coin.CoinService;
import com.crypto.exchange.contentmng.service.coininfo.CoinInfoService;
import com.crypto.exchange.contentmng.service.meta.MetaService;
import com.crypto.exchange.contentmng.service.tag.TagService;
import com.gd.core.ErrorMessage;
import com.gd.core.RuleException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/coinInfo")
public class CoinInfoController {

    private final CoinInfoService coinInfoService;
    private final CoinService coinService;
    private final MetaService metaService;
    private final MessageSourceAccessor messageSourceAccessor;
    private final TagService tagService;
    private final Validator validatorCoinInfoModify;

    public CoinInfoController(CoinInfoService coinInfoService
            , CoinService coinService
            , MetaService metaService
            , MessageSourceAccessor messageSourceAccessor
            , TagService tagService
            , @Qualifier("validatorCoinInfoModify") Validator validatorCoinInfoModify) {
        this.coinInfoService = coinInfoService;
        this.coinService = coinService;
        this.metaService = metaService;
        this.messageSourceAccessor = messageSourceAccessor;
        this.tagService = tagService;
        this.validatorCoinInfoModify = validatorCoinInfoModify;
    }

    @InitBinder("coinInfoModifyRequest")
    public void binderCoinInfo(WebDataBinder binder) {
        binder.setValidator(validatorCoinInfoModify);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole({'ADMIN','AUTHOR'})")
    public ResponseEntity<CoinInfoAddResponse> saveCoinInfo(@Valid @RequestBody CoinInfoAddRequest coinInfoAddRequest) {
        Currency currency = coinService.findById(coinInfoAddRequest.getCoinId())
                .orElseThrow(() -> new RuleException(ErrorMessage.error(messageSourceAccessor.getMessage("coin.info.save.find.coin.not.found"), "coin.info.save.find.coin.not.found")));
        CoinInfo coinInfo = save(currency, coinInfoAddRequest);
        CoinInfoAddResponse coinInfoAddResponse = CoinInfoAddResponse.builder().id(coinInfo.getId()).build();
        return ResponseEntity.ok(coinInfoAddResponse);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole({'ADMIN','AUTHOR'})")
    public ResponseEntity<Object> modifyCoinInfo(@Valid @RequestBody CoinInfoModifyRequest coinInfoModifyRequest) {
        CoinInfo coinInfo = coinInfoService.findById(coinInfoModifyRequest.getId()).orElse(new CoinInfo());
        metaService.save(setModifyCoinInfo(coinInfo, coinInfoModifyRequest));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id:\\d}")
    public ResponseEntity<CoinInfoGetResponse> getCoinInfo(@PathVariable long id) {
        CoinInfo coinInfo = coinInfoService.findById(id).orElseThrow(
                () -> new RuleException(ErrorMessage.error("find.coin.info", "find.coin.info")));
        CoinInfoGetResponse build = setCoinInfo(coinInfo);
        return ResponseEntity.ok().body(build);
    }

    private CoinInfoGetResponse setCoinInfo(CoinInfo coinInfo) {
        LinkResponse link = LinkResponse.builder()
                .path(coinInfo.getLink().getPath())
                .redirectCode(coinInfo.getLink().getRedirectCode())
                .redirectPath(coinInfo.getLink().getRedirectPath())
                .id(coinInfo.getLink().getId())
                .build();
        return CoinInfoGetResponse.builder()
                .id(coinInfo.getId())
                .description(coinInfo.getDescription())
                .lang(coinInfo.getLang())
                .link(link)
                .title(coinInfo.getTitle())
                .build();
    }

    @Transactional
    public CoinInfo save(Currency currency, CoinInfoAddRequest coinInfoAddRequest) {
        Link link = Link.builder()
                .path(coinInfoAddRequest.getPath())
                .build();

        CoinInfo coinInfo = CoinInfo.builder()
                .title(coinInfoAddRequest.getTitle())
                .description(coinInfoAddRequest.getDescription())
                .link(link)
                .coin(currency)
                .lang(coinInfoAddRequest.getLang())
                .build();
        link.setCoinInfo(coinInfo);

        List<Meta> metas = new ArrayList<>();
        for (String metaRequest : coinInfoAddRequest.getMetas()) {
            Meta meta = Meta.builder()
                    .coinInfo(coinInfo)
                    .value(metaRequest)
                    .build();
            metas.add(meta);
        }

        List<Tag> tags = new ArrayList<>();
        for (String title : coinInfoAddRequest.getTags()) {
            Tag tag = Tag.builder()
                    .setCoinInfo(coinInfo)
                    .setTitle(title)
                    .build();
            tags.add(tag);
        }
        coinInfoService.save(coinInfo);
        metaService.saveAll(metas);
        tagService.savAll(tags);
        return metaService.saveAll(metas);
    }

    @Transactional
    public Meta setModifyCoinInfo(CoinInfo coinInfo, CoinInfoModifyRequest coinInfoModifyRequest) {
        Link link = coinInfo.getLink();
        coinInfo.setLink(link);
        if (!(link.getPath().equals(coinInfoModifyRequest.getLink().getPath()))) {
            link.setDeleted(true);
            Link newLink = Link.builder()
                    .coinInfo(coinInfo)
                    .path(coinInfoModifyRequest.getLink().getPath())
                    .redirectCode(coinInfoModifyRequest.getLink().getRedirectCode())
                    .redirectUser(coinInfoModifyRequest.getLink().getRedirectPath())
                    .build();
            coinInfo.setLink(newLink);
        }
        coinInfo.setTitle(coinInfoModifyRequest.getTitle());
        coinInfo.setDescription(coinInfoModifyRequest.getDescription());
        coinInfo.setLang(coinInfoModifyRequest.getLang());

        coinInfoService.save(coinInfo);
        return Meta.builder()
                .coinInfo(coinInfo)
                .value(coinInfoModifyRequest.getCanonical())
                .build();
    }

}
