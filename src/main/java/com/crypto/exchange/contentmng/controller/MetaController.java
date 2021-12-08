package com.crypto.exchange.contentmng.controller;

import com.crypto.exchange.contentmng.dto.request.MetaModifyRequest;
import com.crypto.exchange.contentmng.dto.request.SaveMetaRequest;
import com.crypto.exchange.contentmng.model.CoinInfo;
import com.crypto.exchange.contentmng.model.Meta;
import com.crypto.exchange.contentmng.service.coininfo.CoinInfoService;
import com.crypto.exchange.contentmng.service.meta.MetaService;
import com.gd.core.ErrorMessage;
import com.gd.core.RuleException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/metas")
public class MetaController {

    private final MessageSourceAccessor messageSourceAccessor;
    private final MetaService metaService;
    private final CoinInfoService coinInfoService;

    public MetaController(MessageSourceAccessor messageSourceAccessor, MetaService metaService, CoinInfoService coinInfoService) {
        this.messageSourceAccessor = messageSourceAccessor;
        this.metaService = metaService;
        this.coinInfoService = coinInfoService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole({'ADMIN','AUTHOR'})")
    public ResponseEntity<Object> save(@Valid @RequestBody SaveMetaRequest saveMetaRequest) {
        CoinInfo coinInfo = coinInfoService.findById(saveMetaRequest.getCoinId()).orElseThrow(
                () -> new RuleException(ErrorMessage.error(messageSourceAccessor.getMessage("save.meta.coin.info"), "save.meta.coin.info")));
        Meta meta = Meta.builder()
                .coinInfo(coinInfo)
                .value(saveMetaRequest.getValue())
                .build();
        metaService.save(meta);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole({'ADMIN','AUTHOR'})")
    public ResponseEntity<Object> modify(@Valid @RequestBody MetaModifyRequest metaModifyRequest) {
        Meta meta = metaService.findById(metaModifyRequest.getId()).orElseThrow(
                () -> new RuleException(ErrorMessage.error(messageSourceAccessor.getMessage("meta.modify.find.id"), "meta.modify.find.id")));
        metaService.save(setModify(meta, metaModifyRequest));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id:\\d}")
    @PreAuthorize("hasAnyRole({'ADMIN','AUTHOR'})")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        Meta meta = metaService.findById(id).orElseThrow(
                () -> new RuleException(ErrorMessage.error(messageSourceAccessor.getMessage("meta.delete.find"), "meta.delete.find")));
        metaService.deleteMeta(meta);
        return ResponseEntity.ok().build();
    }


    private Meta setModify(Meta meta, MetaModifyRequest metaModifyRequest) {
        meta.setValue(metaModifyRequest.getValue());
        return meta;
    }
}
