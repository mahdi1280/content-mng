package com.crypto.exchange.contentmng.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

public class TagDeleteRequest implements Serializable {

    private final List<Integer> tags;

    @JsonCreator
    public TagDeleteRequest(List<Integer> tags) {
        this.tags = tags;
    }

    @Valid
    public List<Integer> getTags() {
        return tags;
    }
}
