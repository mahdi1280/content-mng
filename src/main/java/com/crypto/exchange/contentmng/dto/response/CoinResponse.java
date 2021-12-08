package com.crypto.exchange.contentmng.dto.response;

public class CoinResponse {

    private final long id;

    public CoinResponse(long id) {
        this.id = id;
    }

    public static CoinInfoAddResponse.Builder builder() {
        return new CoinInfoAddResponse.Builder();
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

        public CoinResponse build() {
            return new CoinResponse(id);
        }
    }
}
