package com.gd.contentmng.controller;

import com.gd.contentmng.dto.request.CoinInfoAddRequest;
import com.gd.contentmng.dto.request.CoinInfoModifyRequest;
import com.gd.contentmng.dto.response.CoinInfoAddResponse;
import com.gd.contentmng.model.*;
import com.gd.contentmng.service.coin.CoinService;
import com.gd.contentmng.service.coininfo.CoinInfoService;
import com.gd.contentmng.service.meta.MetaService;
import com.gd.contentmng.service.tag.TagService;
import com.gd.contentmng.validator.ValidatorCoinInfoModify;
import com.gd.core.ErrorMessage;
import com.gd.core.RuleException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
//    @PreAuthorize("hasAnyAuthority({'ADMIN','VENDOR'})")
    public ResponseEntity<CoinInfoAddResponse> saveCoinInfo(@Valid @RequestBody CoinInfoAddRequest coinInfoAddRequest) {
        Coin coin = coinService.findByCode(coinInfoAddRequest.getCoinId())
                .orElseThrow(() -> new RuleException(ErrorMessage.error(messageSourceAccessor.getMessage("coin.info.save.find.coin.not.found"), "coin.info.save.find.coin.not.found")));
        CoinInfo coinInfo = save(coin, coinInfoAddRequest);
        CoinInfoAddResponse coinInfoAddResponse = CoinInfoAddResponse.builder().id(coinInfo.getId()).build();
        return ResponseEntity.ok(coinInfoAddResponse);
    }

    @PutMapping
    public ResponseEntity<Object> modifyCoinInfo(@Valid @RequestBody CoinInfoModifyRequest coinInfoModifyRequest
            , BindingResult result) {
        if(result.hasFieldErrors("coinInfo")){
            throw new RuleException(ErrorMessage.error(messageSourceAccessor.getMessage("find.coin.info.modify"), "find.coin.info.modify"));
        }else if(result.hasFieldErrors("redirectCode")){
            throw new RuleException(ErrorMessage.error(messageSourceAccessor.getMessage("find.coin.info.modify.redirectCode"), "find.coin.info.modify.redirectCode"));
        }else if(result.hasFieldErrors("redirectPath")){
            throw new RuleException(ErrorMessage.error(messageSourceAccessor.getMessage("find.coin.info.modify.redirectPath"), "find.coin.info.modify.redirectPath"));
        }
        CoinInfo coinInfo = coinInfoService.findById(coinInfoModifyRequest.getId()).orElse(new CoinInfo());
        metaService.save(setModifyCoinInfo(coinInfo, coinInfoModifyRequest));
        return ResponseEntity.ok().build();
    }

    @Transactional
    public CoinInfo save(Coin coin, CoinInfoAddRequest coinInfoAddRequest) {
        Link link = Link.builder()
                .path(coinInfoAddRequest.getPath())
                .build();

        CoinInfo coinInfo = CoinInfo.builder()
                .title(coinInfoAddRequest.getTitle())
                .description(coinInfoAddRequest.getDescription())
                .link(link)
                .coin(coin)
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

        return Meta.builder()
                .coinInfo(coinInfo)
                .value(coinInfoModifyRequest.getCanonical())
                .build();
    }

}
