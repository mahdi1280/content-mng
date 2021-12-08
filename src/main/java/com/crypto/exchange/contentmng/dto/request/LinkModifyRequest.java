package com.crypto.exchange.contentmng.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@JsonDeserialize(builder = LinkModifyRequest.Builder.class)
public class LinkModifyRequest implements Serializable {

    private final String path;
    private final String redirectPath;
    private final String redirectCode;

    public LinkModifyRequest(String path, String redirectPath, String redirectCode) {
        this.path = path;
        this.redirectPath = redirectPath;
        this.redirectCode = redirectCode;
    }

    public static Builder builder() {
        return new Builder();
    }

    @NotNull(message = "{link.path.null}")
    @NotBlank(message = "{link.path.empty}")
    public String getPath() {
        return path;
    }

    public String getRedirectPath() {
        return redirectPath;
    }

    @Size(max = 3, message = "{link.redirect.code.size}")
    public String getRedirectCode() {
        return redirectCode;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {

        private String path;
        private String redirectPath;
        private String redirectCode;

        public Builder() {
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

        public LinkModifyRequest build() {
            return new LinkModifyRequest(path, redirectPath, redirectCode);
        }
    }
}
