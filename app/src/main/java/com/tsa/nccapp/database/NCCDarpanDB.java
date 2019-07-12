package com.tsa.nccapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.tsa.nccapp.DAO.NotificationDAO;
import com.tsa.nccapp.models.OtherExamModel;

/**
 * Created by RajkumarMann on 15-02-2018.
 */

@Database(entities = { OtherExamModel.class }, version = 2,exportSchema = false)
public abstract class NCCDarpanDB extends RoomDatabase {

    private static final String DB_NAME = "nccdarpandb.db";
    private static volatile NCCDarpanDB instance;

    public static synchronized NCCDarpanDB getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static NCCDarpanDB create(final Context context) {
        return Room.databaseBuilder(
                context,
                NCCDarpanDB.class,
                DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    public abstract NotificationDAO getNccDarpanDao();
}
