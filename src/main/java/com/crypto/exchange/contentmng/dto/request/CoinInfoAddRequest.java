package com.crypto.exchange.contentmng.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(builder = CoinInfoAddRequest.Builder.class)
public class CoinInfoAddRequest implements Serializable {

    private final String title;
    private final String description;
    private final List<String> metas;
    private final String path;
    private final List<String> tags;
    private final int coinId;
    private final String lang;

    public CoinInfoAddRequest(String title, String description, List<String> metas, String path, List<String> tags, int coinId,String lang) {
        this.title = title;
        this.description = description;
        this.metas = metas;
        this.path = path;
        this.tags = tags;
        this.coinId = coinId;
        this.lang= lang;
    }

    public static Builder builder() {
        return new Builder();
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

    @Valid
    @NotEmpty(message = "{coin.info.add.request.meta.request.empty}")
    public List<String> getMetas() {
        return metas;
    }

    @NotBlank(message = "{coin.info.add.request.path.blank}")
    @NotNull(message = "{coin.info.add.request.path.null}")
    public String getPath() {
        return path;
    }

    @NotEmpty(message = "{coin.info.add.request.tag.empty}")
    public List<String> getTags() {
        return tags;
    }

    @Min(value = 1, message = "{coin.info.add.request.coin.code.null}")
    public int getCoinId() {
        return coinId;
    }

    @NotNull(message = "{coin.info.add.request.lang.null}")
    @NotBlank(message = "{coin.info.add.request.lang.blank}")
    @Size(min = 1,max = 5,message = "{coin.info.add.request.lang.size}")
    public String getLang() {
        return lang;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {

        private String title;
        private String description;
        private List<String> metas = new ArrayList<>();
        private String path;
        private List<String> tags = new ArrayList<>();
        private int coinId;
        private String lang;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder metas(List<String> metas) {
            this.metas = metas;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder tags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public Builder coinId(int coinId) {
            this.coinId = coinId;
            return this;
        }

        public Builder lang(String lang) {
            this.lang = lang;
            return this;
        }

        public CoinInfoAddRequest build() {
            return new CoinInfoAddRequest(title, description, metas, path, tags, coinId,lang);
        }

    }
}
