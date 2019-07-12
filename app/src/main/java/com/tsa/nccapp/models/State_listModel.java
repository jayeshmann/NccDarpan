package com.tsa.nccapp.models;

/**
 * Created by hp on 27-Dec-2018.
 */
import com.google.gson.annotations.SerializedName;

public class State_listModel {

    @SerializedName("id")
    private String mId;
    @SerializedName("state_name")
    private String mStateName;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getStateName() {
        return mStateName;
    }

    public void setStateName(String stateName) {
        mStateName = stateName;
    }

}