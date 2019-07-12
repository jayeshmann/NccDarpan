
package com.tsa.nccapp.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class NotificationModel {

    private String mDate;
    private String mHead;
    private String mImgname;
    private String mName;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String mNid;
    private String mView;
    private String url;
    private String wish;

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getHead() {
        return mHead;
    }

    public void setHead(String head) {
        mHead = head;
    }

    public String getImgname() {
        return mImgname;
    }

    public void setImgname(String imgname) {
        mImgname = imgname;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNid() {
        return mNid;
    }

    public void setNid(String nid) {
        mNid = nid;
    }

    public String getView() {
        return mView;
    }

    public void setView(String view) {
        mView = view;
    }
}
