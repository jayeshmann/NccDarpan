package com.tsa.nccapp.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.tsa.nccapp.models.OtherExamModel;

import java.util.List;

/**
 * Created by Akhil Tripathi on 15-02-2018.
 */
@Dao
public interface NotificationDAO {

    @Query("SELECT * from otherexammodel")
    public List<OtherExamModel> loadAllQustions();

    @Insert
    void insertExamQuestions(OtherExamModel... otherExamModel);

    @Update
    void updateExamQuestions(OtherExamModel... otherExamModel);

    @Delete
    void deleteExamQuestions(OtherExamModel... otherExamModel);

    @Query("DELETE FROM otherexammodel")
    public void deleteTable();

    @Query("SELECT * from otherexammodel where questionID = :id")
    public List<OtherExamModel> getByQID(int id);
}
