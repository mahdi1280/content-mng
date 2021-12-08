package com.crypto.exchange.contentmng.model;

import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Audited
@Table(schema = Schema.SCHEMA_NAME)
public class Currency extends BaseEntity {

    private String name;
    private String code;
    private String title;
    private Boolean enabled= true;
    private byte[] logo;
    private CurrencyType currencyType;

    public Currency(String name, String code, String title, byte[] logo,CurrencyType currencyType) {
        this.name = name;
        this.code = code;
        this.title = title;
        this.logo = logo;
        this.currencyType = currencyType;
    }

    public Currency() {
    }

    public static Builder builder(){
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(columnDefinition = "boolean default true")
    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    @ManyToOne
    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public static class Builder{

        private String name;
        private String code;
        private String title;
        private byte[] logo;
        private CurrencyType currencyType;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setLogo(byte[] logo) {
            this.logo = logo;
            return this;
        }

        public Builder setCurrencyType(CurrencyType currencyType) {
            this.currencyType = currencyType;
            return this;
        }

        public Currency build(){
            return new Currency(name,code,title,logo,currencyType);
        }
    }
}
