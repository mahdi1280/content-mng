package com.gd.contentmng.validator;

import com.gd.contentmng.dto.request.CoinInfoModifyRequest;
import com.gd.contentmng.model.CoinInfo;
import com.gd.contentmng.service.coininfo.CoinInfoService;
import com.gd.core.ErrorMessage;
import com.gd.core.RuleException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
        if(coinInfo == null){
            errors.rejectValue("coinInfo", "empty");
        }
        else if (!Objects.equals(coinInfoModifyRequest.getLink().getPath(), coinInfo.getLink().getPath())) {
            if (coinInfoModifyRequest.getLink().getRedirectCode().isEmpty()
                    || coinInfoModifyRequest.getLink().getRedirectCode() == null){
                errors.rejectValue("redirectCode", "empty.field.");
            }
            if (coinInfoModifyRequest.getLink().getRedirectPath().isEmpty()
                    || coinInfoModifyRequest.getLink().getRedirectPath() == null){
                errors.rejectValue("redirectPath", "empty");
            }
        }
    }
}
