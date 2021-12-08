package com.crypto.exchange.contentmng.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.io.Serializable;

@JsonDeserialize(builder = LinkResponse.Builder.class)
public class LinkResponse implements Serializable {

    private final long id;
    private final String path;
    private final String redirectPath;
    private final String redirectCode;

    public LinkResponse(long id, String path, String redirectPath, String redirectCode) {
        this.id = id;
        this.path = path;
        this.redirectPath = redirectPath;
        this.redirectCode = redirectCode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public String getRedirectPath() {
        return redirectPath;
    }

    public String getRedirectCode() {
        return redirectCode;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {

        private long id;
        private String path;
        private String redirectPath;
        private String redirectCode;

        public Builder() {
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder redirectPath(String redirectPath) {
            this.redirectPath = redirectPath;
            return this;
        }

        public Builder redirectCode(String redirectCode) {
            this.redirectCode = redirectCode;
            return this;
        }

        public LinkResponse build() {
            return new LinkResponse(id, path, redirectPath, redirectCode);
        }
    }
}
