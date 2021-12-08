package com.crypto.exchange.contentmng.model;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Audited
@Table(schema = Schema.SCHEMA_NAME)
public class Faq extends BaseEntity {

    private String question;
    private String answer;

    private CoinInfo coinInfo;

    public Faq(String question, String answer, CoinInfo coinInfo) {
        this.question = question;
        this.answer = answer;
        this.coinInfo = coinInfo;
    }

    public Faq() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    public CoinInfo getCoinInfo() {
        return coinInfo;
    }

    public void setCoinInfo(CoinInfo coinInfo) {
        this.coinInfo = coinInfo;
    }

    public static class Builder {

        private String question;
        private String answer;

        private CoinInfo coinInfo;

        public Builder() {
        }

        public Builder question(String question) {
            this.question = question;
            return this;
        }

        public Builder answer(String answer) {
            this.answer = answer;
            return this;
        }

        public Builder coinInfo(CoinInfo coinInfo) {
            this.coinInfo = coinInfo;
            return this;
        }

        public Faq build() {
            return new Faq(answer, question, coinInfo);
        }
    }
}
