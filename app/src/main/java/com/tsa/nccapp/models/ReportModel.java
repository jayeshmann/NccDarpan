package com.tsa.nccapp.models;

/**
 * Created by Akhil Tripathi on 24-01-2018.
 */

public class ReportModel {

    public ReportModel(MockQModel mockQModel,String mAttempted, String mQStatus) {
        this.mAnswer = mockQModel.getAnswer();
        this.mCreatedDate = getmCreatedDate();
        this.mId =mockQModel.getId();
        this.mIsDeleted =mockQModel.getIsDeleted();
        this.mMockId = mockQModel.getMockId();
        this.mOptionA =mockQModel. getOptionA();
        this.mOptionB =mockQModel. getOptionB();
        this.mOptionC = mockQModel.getOptionC();
        this.mOptionD = mockQModel.getOptionD();
        this.mQuestion = mockQModel.getQuestion();
        this.mStatus = mockQModel.getStatus();
        this.mAttempted = mAttempted;
        this.mQStatus = mQStatus;
    }

    private String mAnswer;
    private String mCreatedDate;
    private String mId;
    private String mIsDeleted;
    private String mMockId;
    private String mOptionA;
    private String mOptionB;
    private String mOptionC;
    private String mOptionD;
    private String mQuestion;
    private String mStatus;
    private String mAttempted;
    private String mQStatus;

    public String getmQStatus() {
        return mQStatus;
    }

    public void setmQStatus(String mQStatus) {
        this.mQStatus = mQStatus;
    }

    public String getmAttempted() {
        return mAttempted;
    }

    public void setmAttempted(String mAttempted) {
        this.mAttempted = mAttempted;
    }

    public ReportModel() {
    }

    public String getmAnswer() {
        return mAnswer;
    }

    public void setmAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }

    public String getmCreatedDate() {
        return mCreatedDate;
    }

    public void setmCreatedDate(String mCreatedDate) {
        this.mCreatedDate = mCreatedDate;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmIsDeleted() {
        return mIsDeleted;
    }

    public void setmIsDeleted(String mIsDeleted) {
        this.mIsDeleted = mIsDeleted;
    }

    public String getmMockId() {
        return mMockId;
    }

    public void setmMockId(String mMockId) {
        this.mMockId = mMockId;
    }

    public String getmOptionA() {
        return mOptionA;
    }

    public void setmOptionA(String mOptionA) {
        this.mOptionA = mOptionA;
    }

    public String getmOptionB() {
        return mOptionB;
    }

    public void setmOptionB(String mOptionB) {
        this.mOptionB = mOptionB;
    }

    public String getmOptionC() {
        return mOptionC;
    }

    public void setmOptionC(String mOptionC) {
        this.mOptionC = mOptionC;
    }

    public String getmOptionD() {
        return mOptionD;
    }

    public void setmOptionD(String mOptionD) {
        this.mOptionD = mOptionD;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

}
