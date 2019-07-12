package com.tsa.nccapp.models;

public class OtherExamResultModel {
    @Override
    public String toString() {
        return "OtherExamResultModel{" +
                "subjectName='" + subjectName + '\'' +
                ", numberOfQus=" + numberOfQus +
                ", numberOfCurrQus=" + numberOfCurrQus +
                '}';
    }

    private String subjectName;
    private int numberOfQus;
    private int numberOfCurrQus;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getNumberOfQus() {
        return numberOfQus;
    }

    public void setNumberOfQus(int numberOfQus) {
        this.numberOfQus = numberOfQus;
    }

    public int getNumberOfCurrQus() {
        return numberOfCurrQus;
    }

    public void setNumberOfCurrQus(int numberOfCurrQus) {
        this.numberOfCurrQus = numberOfCurrQus;
    }

}
