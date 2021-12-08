package com.crypto.exchange.contentmng.dto.response;

public class FaqResponse {

    private final long id;
    private final String question;
    private final String answer;

    public FaqResponse(long id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
