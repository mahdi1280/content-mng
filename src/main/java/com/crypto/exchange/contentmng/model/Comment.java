package com.crypto.exchange.contentmng.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
@Table(schema = Schema.SCHEMA_NAME)
public class Comment extends BaseEntity {

    private String text;
    private String nickName;
    private boolean approved = true;

    private CoinInfo coinInfo;
    private long userId;
    private Comment parent;

    public Comment(String text, String nickName, CoinInfo coinInfo, long userId, Comment parent) {
        this.text = text;
        this.nickName = nickName;
        this.coinInfo = coinInfo;
        this.userId = userId;
        this.parent = parent;
    }

    public Comment() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nikName) {
        this.nickName = nikName;
    }

    @Column(columnDefinition = "boolean default true")
    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    public CoinInfo getCoinInfo() {
        return coinInfo;
    }

    public void setCoinInfo(CoinInfo coinInfo) {
        this.coinInfo = coinInfo;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public static class Builder {

        private String text;
        private String nikName;

        private CoinInfo coinInfo;
        private long userId;
        private Comment parent;

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setNikName(String nikName) {
            this.nikName = nikName;
            return this;
        }

        public Builder setCoinInfo(CoinInfo coinInfo) {
            this.coinInfo = coinInfo;
            return this;
        }

        public Builder setUserId(long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setParent(Comment parent) {
            this.parent = parent;
            return this;
        }

        public Comment build() {
            return new Comment(text, nikName, coinInfo, userId, parent);
        }
    }
}
