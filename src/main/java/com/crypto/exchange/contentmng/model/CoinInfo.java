package com.crypto.exchange.contentmng.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
@Table(schema = Schema.SCHEMA_NAME)
public class CoinInfo extends BaseEntity {

    private String title;
    private String description;
    private String lang;

    private Currency currency;
    private Link link;

    public CoinInfo(String title, String description, String lang, Currency currency, Link link) {
        this.title = title;
        this.description = description;
        this.lang = lang;
        this.currency = currency;
        this.link = link;
    }

    public CoinInfo() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = Schema.ID)
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = Schema.LINK_ID, referencedColumnName = Schema.ID)
    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public static class Builder {

        private String title;
        private String description;
        private String lang;

        private Currency currency;
        private Link link;

        private Builder() {
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

        public Builder coin(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Builder link(Link link) {
            this.link = link;
            return this;
        }

        public CoinInfo build() {
            return new CoinInfo(title, description, lang, currency, link);
        }
    }

}

