package com.crypto.exchange.contentmng.controller;

import com.crypto.exchange.contentmng.dto.request.TagAddRequest;
import com.crypto.exchange.contentmng.dto.request.TagDeleteRequest;
import com.crypto.exchange.contentmng.model.CoinInfo;
import com.crypto.exchange.contentmng.model.Tag;
import com.crypto.exchange.contentmng.service.coininfo.CoinInfoService;
import com.crypto.exchange.contentmng.service.tag.TagService;
import com.gd.core.ErrorMessage;
import com.gd.core.RuleException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;
    private final MessageSourceAccessor messageSourceAccessor;
    private final CoinInfoService coinInfoService;

    public TagController(TagService tagService
            , MessageSourceAccessor messageSourceAccessor
            , CoinInfoService coinInfoService) {
        this.tagService = tagService;
        this.messageSourceAccessor = messageSourceAccessor;
        this.coinInfoService = coinInfoService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole({'ADMIN','AUTHOR'})")
    public ResponseEntity<Object> save(@Valid @RequestBody TagAddRequest tagAddRequest) {
        CoinInfo coinInfo = coinInfoService.findById(tagAddRequest.getCoinId()).orElseThrow(
                () -> new RuleException(ErrorMessage.error(messageSourceAccessor.getMessage("tag.save.find.coin.info"), "tag.save.find.coin.info")));
        List<Tag> tags = setTag(tagAddRequest, coinInfo);
        tagService.savAll(tags);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole({'ADMIN','AUTHOR'})")
    public ResponseEntity<Object> delete(@Valid @RequestBody TagDeleteRequest tagDeleteRequest) {
        List<Tag> tags = new ArrayList<>();
        for (int tagDelete : tagDeleteRequest.getTags()) {
            Tag tag = tagService.findById(tagDelete).orElseThrow(
                    () -> new RuleException(ErrorMessage.error(messageSourceAccessor.getMessage("find.tag.delete"), String.format("find.tag.delete.%s", tagDelete))));
            tags.add(tag);
        }
        tagService.deleteAll(tags);
        return ResponseEntity.ok().build();
    }

    private List<Tag> setTag(TagAddRequest tagAddRequest, CoinInfo coinInfo) {
        List<Tag> tags = new ArrayList<>();
        for (String tagAdd : tagAddRequest.getTags()) {
            Tag tag = Tag.builder()
                    .setCoinInfo(coinInfo)
                    .setTitle(tagAdd)
                    .build();
            tags.add(tag);
        }
        return tags;
    }
}
