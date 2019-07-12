
package com.tsa.nccapp.models;

public class ChapterListModel {

    private String mChapter;
    private String mChapterName;
    private String mCreatedDate;
    private String mId;
    private String mIsDeleted;
    private String mStatus;
    private String mSubjectId;
    private String mSubjectName;

    public String getSubjectName() {
        return mSubjectName;
    }

    public void setSubjectName(String mSubjectName) {
        this.mSubjectName = mSubjectName;
    }

    public String getChapter() {
        return mChapter;
    }

    public void setChapter(String chapter) {
        mChapter = chapter;
    }

    public String getChapterName() {
        return mChapterName;
    }

    public void setChapterName(String chapterName) {
        mChapterName = chapterName;
    }

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        mCreatedDate = createdDate;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getIsDeleted() {
        return mIsDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        mIsDeleted = isDeleted;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getSubjectId() {
        return mSubjectId;
    }

    public void setSubjectId(String subjectId) {
        mSubjectId = subjectId;
    }

}
