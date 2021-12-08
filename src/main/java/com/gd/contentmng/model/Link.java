package com.gd.contentmng.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
public class Link extends BaseEntity{

    private String path;
    private String redirectPath;
    private String redirectCode;
    private boolean deleted = false;

    private CoinInfo coinInfo;

    public Link(String path, String redirectPath, String redirectCode, CoinInfo coinInfo) {
        this.path = path;
        this.redirectPath = redirectPath;
        this.redirectCode = redirectCode;
        this.coinInfo = coinInfo;
    }

    public Link() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRedirectPath() {
        return redirectPath;
    }

    public void setRedirectPath(String redirectPath) {
        this.redirectPath = redirectPath;
    }

    public String getRedirectCode() {
        return redirectCode;
    }

    public void setRedirectCode(String redirectCode) {
        this.redirectCode = redirectCode;
    }

    @Column(columnDefinition = "boolean default false")
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @OneToOne(mappedBy = "link")
    public CoinInfo getCoinInfo() {
        return coinInfo;
    }

    public void setCoinInfo(CoinInfo coinInfo) {
        this.coinInfo = coinInfo;
    }

    public static class Builder {

        private String path;
        private String redirectPath;
        private String redirectCode;

        private CoinInfo coinInfo;

        public Builder() {
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder redirectUser(String redirectUser) {
            this.redirectPath = redirectUser;
            return this;
        }

        public Builder redirectCode(String redirectCode) {
            this.redirectCode = redirectCode;
            return this;
        }


        public Builder coinInfo(CoinInfo coinInfo) {
            this.coinInfo = coinInfo;
            return this;
        }

        public Link build() {
            return new Link(path, redirectPath, redirectCode, coinInfo);
        }


    }
}
