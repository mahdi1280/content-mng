package com.crypto.exchange.contentmng.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

public class TagAdd implements Serializable {

    private final String title;

    @JsonCreator
    public TagAdd(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
