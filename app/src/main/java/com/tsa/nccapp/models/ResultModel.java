package com.tsa.nccapp.models;

/**
 * Created by Akhil Tripathi on 16-01-2018.
 */

public class ResultModel {

    String examDate="";
    String noOfQuestions = "";

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getNoOfQuestions() {
        return noOfQuestions;
    }

    public void setNoOfQuestions(String noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
    }

    public String getMarksOpt() {
        return marksOpt;
    }

    public void setMarksOpt(String marksOpt) {
        this.marksOpt = marksOpt;
    }

    public String getNoOFCurrect() {
        return noOFCurrect;
    }

    public void setNoOFCurrect(String noOFCurrect) {
        this.noOFCurrect = noOFCurrect;
    }

    String marksOpt = "";
    String noOFCurrect = "";
}
