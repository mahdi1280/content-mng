package com.gd.contentmng.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class SaveMetaRequest implements Serializable {

    private final String value;
    private final int coinId;

    @JsonCreator
    public SaveMetaRequest(String value, int coinId) {
        this.value = value;
        this.coinId = coinId;
    }

    @NotBlank(message = "{save.meta.request.value.blank}")
    @NotNull(message = "{save.meta.request.value.null}")
    public String getValue() {
        return value;
    }

    @Min(value = 1,message = "{save.meta.request.coin.id.min}")
    public int getCoinId() {
        return coinId;
    }
}
