package com.tsa.nccapp.models;

/**
 * Created by RajkumarMann on 21-02-2018.
 */

public class FAQModel {
    private String question;
    private String ans;
    private String faqID;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getFaqID() {
        return faqID;
    }

    public void setFaqID(String faqID) {
        this.faqID = faqID;
    }
}
