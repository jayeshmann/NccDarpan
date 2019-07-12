
package com.tsa.nccapp.models;

import java.util.List;

public class CatListModel {

    private String mCategoryId;
    private String mCategoryName;
    private List<SubjectList> mSubjectList;

    public String getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(String categoryId) {
        mCategoryId = categoryId;
    }

    public String getCategoryName() {
        return mCategoryName;
    }

    public void setCategoryName(String categoryName) {
        mCategoryName = categoryName;
    }

    public List<SubjectList> getSubjectList() {
        return mSubjectList;
    }

    public void setSubjectList(List<SubjectList> subjectList) {
        mSubjectList = subjectList;
    }

}
