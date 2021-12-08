package com.crypto.exchange.contentmng.validator;

import com.crypto.exchange.contentmng.dto.request.CoinInfoModifyRequest;
import com.crypto.exchange.contentmng.model.CoinInfo;
import com.crypto.exchange.contentmng.service.coininfo.CoinInfoService;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class ValidatorCoinInfoModify implements Validator {
    private final CoinInfoService coinInfoService;
    private final MessageSourceAccessor messageSourceAccessor;

    public ValidatorCoinInfoModify(CoinInfoService coinInfoService, MessageSourceAccessor messageSourceAccessor) {
        this.coinInfoService = coinInfoService;
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CoinInfoModifyRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CoinInfoModifyRequest coinInfoModifyRequest = (CoinInfoModifyRequest) target;
        CoinInfo coinInfo = coinInfoService.findById(coinInfoModifyRequest.getId()).orElse(null);
        if (coinInfo == null) {
            errors.rejectValue("coinInfo", "empty");
        } else if (!Objects.equals(coinInfoModifyRequest.getLink().getPath(), coinInfo.getLink().getPath())) {
            if (coinInfoModifyRequest.getLink().getRedirectCode() == null) {
                errors.rejectValue("link.redirectCode", "empty"
                        , messageSourceAccessor.getMessage("link.redirectCode"));
            }
            if (coinInfoModifyRequest.getLink().getRedirectCode() != null) {
                if (coinInfoModifyRequest.getLink().getRedirectCode().isEmpty())
                    errors.rejectValue("link.redirectCode", "empty"
                            , messageSourceAccessor.getMessage("link.redirectCode"));
                if (coinInfoModifyRequest.getLink().getRedirectCode().length() > 3)
                    errors.rejectValue("link.redirectCode", "size"
                            , messageSourceAccessor.getMessage("link.redirect.code.size"));
            }
            if (coinInfoModifyRequest.getLink().getRedirectPath() == null) {
                errors.rejectValue("link.redirectPath", "empty"
                        , messageSourceAccessor.getMessage("link.redirectPath"));
            }
            if (coinInfoModifyRequest.getLink().getRedirectPath() != null) {
                if (coinInfoModifyRequest.getLink().getRedirectPath().isEmpty())
                    errors.rejectValue("link.redirectPath", "empty"
                            , messageSourceAccessor.getMessage("link.redirectPath"));
                if(coinInfoModifyRequest.getLink().getRedirectPath().length()>60)
                    errors.rejectValue("link.redirectPath", "empty"
                            , messageSourceAccessor.getMessage("link.redirectPath.size"));
            }
        }
    }
}
