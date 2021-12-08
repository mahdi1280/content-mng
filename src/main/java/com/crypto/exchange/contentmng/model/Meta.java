package com.crypto.exchange.contentmng.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
@Table(schema = Schema.SCHEMA_NAME)
public class Meta extends BaseEntity {

    private String value;

    private boolean deleted = false;

    private CoinInfo coinInfo;

    public Meta(String value, CoinInfo coinInfo) {
        this.value = value;
        this.coinInfo = coinInfo;
    }

    public Meta() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(columnDefinition = "boolean default false")
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    public CoinInfo getCoinInfo() {
        return coinInfo;
    }

    public void setCoinInfo(CoinInfo coinInfo) {
        this.coinInfo = coinInfo;
    }

    public static class Builder {

        private String value;

        private CoinInfo coinInfo;

        public Builder() {
        }

        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public Builder coinInfo(CoinInfo coinInfo) {
            this.coinInfo = coinInfo;
            return this;
        }

        public Meta build() {
            return new Meta(value, coinInfo);
        }
    }
}
