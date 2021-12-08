package com.gd.contentmng.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@JsonDeserialize(builder = FaqAddRequest.Builder.class)
public class FaqAddRequest implements Serializable {

    private final String question;
    private final String answer;


    public FaqAddRequest(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
    public static Builder builder(){
        return new Builder();
    }

    @Size(min = 10, message = "{faq.add.request.question.size}")
    @NotBlank(message = "{faq.add.request.question.blank}")
    @NotNull(message = "{faq.add.request.question.null}")
    public String getQuestion() {
        return question;
    }

    @Size(min = 10, message = "{faq.add.request.answer.size}")
    @NotBlank(message = "{faq.add.request.answer.blank}")
    @NotNull(message = "{faq.add.request.answer.null}")
    public String getAnswer() {
        return answer;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder{

        private  String question;
        private  String answer;

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

        public FaqAddRequest build(){
            return new FaqAddRequest(question,answer);
        }
    }
}
