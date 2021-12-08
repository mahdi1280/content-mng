package com.gd.contentmng.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;

@JsonDeserialize(builder = CoinInfoModifyRequest.Builder.class)
public class CoinInfoModifyRequest implements Serializable {

    private final int id;
    private final String title;
    private final String description;
    private final String canonical;
    private final String lang;

    private final LinkModifyRequest link;

    public CoinInfoModifyRequest(int id, String title, String description, String canonical, String lang, LinkModifyRequest link) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.canonical = canonical;
        this.lang = lang;
        this.link = link;
    }

    public static Builder builder(){
        return new Builder();
    }

    @Min(value = 1, message = "{coin.info.modify.request.min.id}")
    public int getId() {
        return id;
    }

    @Size(min = 20, max = 64, message = "{coin.info.add.request.title.size}")
    @NotBlank(message = "{coin.info.add.request.title.blank}")
    @NotNull(message = "{coin.info.add.request.title.null}")
    public String getTitle() {
        return title;
    }

    @Size(min = 10, max = 165, message = "{coin.info.add.request.description.size}")
    @NotBlank(message = "{coin.info.add.request.description.blank}")
    @NotNull(message = "{coin.info.add.request.description.null}")
    public String getDescription() {
        return description;
    }

    @NotEmpty(message = "{coin.info.modify.request.canonical.empty}")
    @NotNull(message = "{coin.info.modify.request.canonical.null}")
    public String getCanonical() {
        return canonical;
    }

    @NotEmpty(message = "{coin.info.modify.request.lang.empty}")
    @NotNull(message = "{coin.info.modify.request.lang.null}")
    public String getLang() {
        return lang;
    }

    @Valid
    @NotNull(message = "{coin.info.modify.request.null.link}")
    public LinkModifyRequest getLink() {
        return link;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {

        private int id;
        private String title;
        private String description;
        private String canonical;
        private String lang;

        private LinkModifyRequest link;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder canonical(String canonical) {
            this.canonical = canonical;
            return this;
        }

        public Builder lang(String lang) {
            this.lang = lang;
            return this;
        }

        public Builder link(LinkModifyRequest link) {
            this.link = link;
            return this;
        }

        public CoinInfoModifyRequest build() {
            return new CoinInfoModifyRequest(id, title, description, canonical, lang, link);
        }
    }
}
