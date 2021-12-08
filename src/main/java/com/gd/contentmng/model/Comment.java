package com.gd.contentmng.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
public class Comment extends BaseEntity{

    private String text;
    private String nikName;
    private boolean approved = true;

    private CoinInfo coinInfo;
    private int userId;
    private Comment parent;

    public Comment(String text, String nikName, CoinInfo coinInfo, int userId, Comment parent) {
        this.text = text;
        this.nikName = nikName;
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

    public String getNikName() {
        return nikName;
    }

    public void setNikName(String nikName) {
        this.nikName = nikName;
    }

    @Column(columnDefinition = "boolean default true")
    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    public CoinInfo getCoinInfo() {
        return coinInfo;
    }

    public void setCoinInfo(CoinInfo coinInfo) {
        this.coinInfo = coinInfo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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
        private int userId;
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

        public Builder setUserId(int userId) {
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
