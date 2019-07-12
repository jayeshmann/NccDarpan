
package com.tsa.nccapp.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(primaryKeys = {"subID", "questionID"})
public class OtherExamModel {

    private String opimg4;

    private String opimg3;

    private String opimg2;

    private String opimg1;

    private String qimg;

    private String ans;

    private String oqo4;

    private String oqo3;

    private String oqo2;

    private String oqo1;

    private String oq;

    private String eqo4;

    private String eqo3;

    private String eqo2;

    private String eqo1;

    private String eq;

    private String el;

    private String sn;

    private String selectedOpt;

    @NonNull
    private String questionID;

    private String qid;

    @NonNull
    private String subID;

    private String subjectName;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSelectedOpt() {
        return selectedOpt;
    }

    public void setSelectedOpt(String selectedOpt) {
        this.selectedOpt = selectedOpt;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getOpimg4() {
        return opimg4;
    }

    public void setOpimg4(String opimg4) {
        this.opimg4 = opimg4;
    }

    public String getOpimg3() {
        return opimg3;
    }

    public void setOpimg3(String opimg3) {
        this.opimg3 = opimg3;
    }

    public String getOpimg2() {
        return opimg2;
    }

    public void setOpimg2(String opimg2) {
        this.opimg2 = opimg2;
    }

    public String getOpimg1() {
        return opimg1;
    }

    public void setOpimg1(String opimg1) {
        this.opimg1 = opimg1;
    }

    public String getQimg() {
        return qimg;
    }

    public void setQimg(String qimg) {
        this.qimg = qimg;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getOqo4() {
        return oqo4;
    }

    public void setOqo4(String oqo4) {
        this.oqo4 = oqo4;
    }

    public String getOqo3() {
        return oqo3;
    }

    public void setOqo3(String oqo3) {
        this.oqo3 = oqo3;
    }

    public String getOqo2() {
        return oqo2;
    }

    public void setOqo2(String oqo2) {
        this.oqo2 = oqo2;
    }

    public String getOqo1() {
        return oqo1;
    }

    public void setOqo1(String oqo1) {
        this.oqo1 = oqo1;
    }

    public String getOq() {
        return oq;
    }

    public void setOq(String oq) {
        this.oq = oq;
    }

    public String getEqo4() {
        return eqo4;
    }

    public void setEqo4(String eqo4) {
        this.eqo4 = eqo4;
    }

    public String getEqo3() {
        return eqo3;
    }

    public void setEqo3(String eqo3) {
        this.eqo3 = eqo3;
    }

    public String getEqo2() {
        return eqo2;
    }

    public void setEqo2(String eqo2) {
        this.eqo2 = eqo2;
    }

    public String getEqo1() {
        return eqo1;
    }

    public void setEqo1(String eqo1) {
        this.eqo1 = eqo1;
    }

    public String getEq() {
        return eq;
    }

    public void setEq(String eq) {
        this.eq = eq;
    }

    public String getEl() {
        return el;
    }

    public void setEl(String el) {
        this.el = el;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getSubID() {
        return subID;
    }

    public void setSubID(String subID) {
        this.subID = subID;
    }

}
