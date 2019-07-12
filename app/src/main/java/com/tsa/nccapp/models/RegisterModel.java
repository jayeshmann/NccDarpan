package com.tsa.nccapp.models;

/**
 * Created by hp on 27-Dec-2018.
 */

public class RegisterModel {


    private String regiment_number;
    private String rank;
    private String name_of_cadet;
    private String email;
    private String mobile;
    private String father_name;
    private String exam_type;
    private String unit;
    private String ncc_group;
    private String state;
    private String instt_year;


    private String district;
    private String town;
    private String usertype;

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }



    public String getInstt_year() {
        return instt_year;
    }

    public void setInstt_year(String instt_year) {
        this.instt_year = instt_year;
    }




    public String getRegiment_number() {
        return regiment_number;
    }

    public void setRegiment_number(String regiment_number) {
        this.regiment_number = regiment_number;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getName_of_cadet() {
        return name_of_cadet;
    }

    public void setName_of_cadet(String name_of_cadet) {
        this.name_of_cadet = name_of_cadet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getExam_type() {
        return exam_type;
    }

    public void setExam_type(String exam_type) {
        this.exam_type = exam_type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNcc_group() {
        return ncc_group;
    }

    public void setNcc_group(String ncc_group) {
        this.ncc_group = ncc_group;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDirectorate() {
        return directorate;
    }

    public void setDirectorate(String directorate) {
        this.directorate = directorate;
    }

    public String getMembership_status() {
        return membership_status;
    }

    public void setMembership_status(String membership_status) {
        this.membership_status = membership_status;
    }

    private String directorate;
    private String membership_status;

}
