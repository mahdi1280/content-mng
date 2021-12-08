package com.crypto.exchange.contentmng.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class CommentAddRequest implements Serializable {

    private final String text;
    private final String nikName;

    private final int coinId;

    private final int parentId;


    public CommentAddRequest(String text, String nikName, int coinId, int parentId) {
        this.text = text;
        this.nikName = nikName;
        this.coinId = coinId;
        this.parentId = parentId;
    }

    @Size(min = 7,max = 120, message = "{comment.add.request.text.size}")
    @NotNull(message = "{comment.add.request.text.null}")
    public String getText() {
        return text;
    }

    @Size(max = 60,message = "{comment.add.request.nikName.size}")
    public String getNikName() {
        return nikName;
    }

    public int getCoinId() {
        return coinId;
    }

    public int getParentId() {
        return parentId;
    }
}
