package com.crypto.exchange.contentmng.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.io.Serializable;

@JsonDeserialize(builder = CoinInfoGetResponse.Builder.class)
public class CoinInfoGetResponse implements Serializable {

    private final long id;
    private final String title;
    private final String description;
    private final String lang;

    private final LinkResponse link;

    public CoinInfoGetResponse(long id,String title, String description, String lang, LinkResponse link) {
        this.id= id;
        this.title = title;
        this.description = description;
        this.lang = lang;
        this.link = link;
    }

    public long getId() {
        return id;
    }

    public static Builder builder(){
        return new Builder();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLang() {
        return lang;
    }

    public LinkResponse getLink() {
        return link;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder{

        private long id;
        private String title;
        private String description;
        private String lang;

        private LinkResponse link;

        public Builder() {
        }

        public Builder id(long id) {
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

        public Builder lang(String lang) {
            this.lang = lang;
            return this;
        }

        public Builder link(LinkResponse link) {
            this.link = link;
            return this;
        }

        public CoinInfoGetResponse build(){
            return new CoinInfoGetResponse(id,title,description,lang,link);
        }
    }
}
