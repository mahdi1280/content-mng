package com.gd.contentmng.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@JsonDeserialize(builder = FaqCoinAddRequest.Builder.class)
public class FaqCoinAddRequest implements Serializable {

    private final int coinId;
    private final List<FaqAddRequest> faqs;

    public FaqCoinAddRequest(int coinId, List<FaqAddRequest> faqs) {
        this.coinId = coinId;
        this.faqs = faqs;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Min(value=1,message = "{faq.coin.add.request.coin.code.null}")
    public int getCoinId() {
        return coinId;
    }

    @Valid
    @NotNull(message = "{faq.coin.add.request.faq.request.null}")
    @NotEmpty(message = "{faq.coin.add.request.faq.request.empty}")
    public List<FaqAddRequest> getFaqs() {
        return faqs;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {

        private int coinId;
        private List<FaqAddRequest> faqs;

        public Builder() {
        }

        public Builder coinId(int coinId) {
            this.coinId = coinId;
            return this;
        }

        public Builder faqs(List<FaqAddRequest> faqs) {
            this.faqs = faqs;
            return this;
        }

        public FaqCoinAddRequest build(){
            return  new FaqCoinAddRequest(coinId,faqs);
        }

    }
}
