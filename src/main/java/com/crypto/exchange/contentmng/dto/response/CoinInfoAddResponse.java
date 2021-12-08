package com.crypto.exchange.contentmng.dto.response;

import java.io.Serializable;

public class CoinInfoAddResponse implements Serializable {

    private final long id;

    public CoinInfoAddResponse(long id) {
        this.id = id;
    }

    public static Builder builder() {
        return new Builder();
    }

    public long getId() {
        return id;
    }

    public static class Builder {

        private long id;

        public Builder() {
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public CoinInfoAddResponse build() {
            return new CoinInfoAddResponse(id);
        }
    }
}
