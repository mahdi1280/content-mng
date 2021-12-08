package com.crypto.exchange.contentmng.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@JsonDeserialize(builder = FaqModifyRequest.Builder.class)
public class FaqModifyRequest implements Serializable {

    private final int id;
    private final String question;
    private final String answer;

    public FaqModifyRequest(int id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Min(value = 1, message = "{faq.modify.id.valid}")
    public int getId() {
        return id;
    }

    @Size(min = 10,max = 120, message = "{faq.modify.question.size.valid}")
    @NotBlank(message = "{faq.modify.question.blank")
    @NotNull(message = "{faq.modify.question.null}")
    public String getQuestion() {
        return question;
    }

    @Size(min = 10,max = 120, message = "{faq.modify.answer.size}")
    @NotBlank(message = "{faq.modify.answer.blank}")
    @NotNull(message = "{faq.modify.answer.null}")
    public String getAnswer() {
        return answer;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {

        private int id;
        private String question;
        private String answer;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder answer(String answer) {
            this.answer = answer;
            return this;
        }

        public Builder question(String question) {
            this.question = question;
            return this;
        }


        public FaqModifyRequest build() {
            return new FaqModifyRequest(id, question, answer);
        }
    }
}
