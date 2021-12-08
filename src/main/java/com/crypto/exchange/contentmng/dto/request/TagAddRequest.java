package com.crypto.exchange.contentmng.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public class TagAddRequest implements Serializable {

    private final int coinId;
    private final List<String> tags;

    @JsonCreator
    public TagAddRequest(int coinId, List<String> tags) {
        this.coinId = coinId;
        this.tags = tags;
    }

    @Min(value = 1, message = "{tag.add.request.id.min}")
    public int getCoinId() {
        return coinId;
    }

    @NotNull(message = "{tag.add.request.tag.add.null")
    @NotEmpty(message = "{tag.add.request.tag.add.empty}")
    @Size(min = 1,max = 60)
    public List<String> getTags() {
        return tags;
    }
}
