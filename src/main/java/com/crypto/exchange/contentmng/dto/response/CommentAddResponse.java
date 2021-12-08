package com.crypto.exchange.contentmng.dto.response;

import java.io.Serializable;

public class CommentAddResponse implements Serializable {

    private final long id;

    public CommentAddResponse(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
