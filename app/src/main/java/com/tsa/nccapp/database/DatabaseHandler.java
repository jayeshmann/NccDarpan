package com.tsa.nccapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tsa.nccapp.models.NotificationModel;
import com.tsa.nccapp.models.ResultModel;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    static final int DATABASE_VERSION = 2;

    // Database Name
    static final String DATABASE_NAME = "examination_db";

    //tables name
    public static final String TABLE_EXAM = "examination_table";
    public static final String TABLE_NOTIFICATION = "notification_table";


    ///////////////EXAM Table Columns names////////////////
    private static final String N_DATE = "mDate";
    private static final String N_HEAD = "mHead";
    private static final String N_IMAGE = "mImgname";
    private static final String N_NAME = "mName";
    private static final String N_ID = "mNid";
    private static final String N_URL = "url";
    private static final String N_VIEW = "mView";
    /////////////////////////////////////////////////////////////////

    ///////////////Notification Table Columns names////////////////
    private static final String EXAM_DATE = "exam_date";
    private static final String NO_OF_Q = "no_q";
    private static final String MARKS_OPT = "marks";
    private static final String NO_OF_CURRECT_Q = "no_of_curr";
    /////////////////////////////////////////////////////////////////

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ////////////////////////////////////////////////////////////////////////////////////////////
        String CREATE_EXAM_TABLE = "CREATE TABLE " + TABLE_EXAM + "(" + EXAM_DATE + " TEXT,"
                + NO_OF_Q + " TEXT," + MARKS_OPT + " TEXT,"
                + NO_OF_CURRECT_Q + " TEXT" + ")";
        ////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////
        String CREATE_NOTIFICATION_TABLE = "CREATE TABLE " + TABLE_NOTIFICATION + "(" + N_DATE + " TEXT,"
                + N_HEAD + " TEXT," + N_IMAGE + " TEXT,"  + N_NAME + " TEXT," + N_ID + " TEXT,"+ N_URL + " TEXT,"
                + N_VIEW + " TEXT" + ")";
        ////////////////////////////////////////////////////////////////////////////////////////////

        db.execSQL(CREATE_EXAM_TABLE);
        db.execSQL(CREATE_NOTIFICATION_TABLE);

    }

    ////////////////////////////Adding new contact//////////////////////////////////////////////////
    public void addQuestions(ResultModel resultModel, Context context) {
           SQLiteDatabase db = this.getWritableDatabase();
           ContentValues values = new ContentValues();

            values.put(EXAM_DATE, "" + resultModel.getExamDate());
            values.put(NO_OF_Q, "" + resultModel.getNoOfQuestions());
            values.put(MARKS_OPT, "" + resultModel.getMarksOpt());
            values.put(NO_OF_CURRECT_Q, "" + resultModel.getNoOFCurrect());

            // Inserting Row
            db.insert(TABLE_EXAM, null, values);
        
        db.close(); // Closing database connection
    }


    /////////////////////Getting All Contacts//////////////
    public ArrayList<ResultModel> getAllResults() {
        ArrayList<ResultModel> questationList = new ArrayList<ResultModel>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EXAM;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ResultModel resultModel = new ResultModel();
                resultModel.setExamDate(cursor.getString(0));
                resultModel.setNoOfQuestions(cursor.getString(1));
                resultModel.setMarksOpt(cursor.getString(2));
                resultModel.setNoOFCurrect(cursor.getString(3));

                questationList.add(resultModel);
            } while (cursor.moveToNext());

        }

        // return contact list
        return questationList;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////Adding new contact//////////////////////////////////////////////////
    public void addNotification(NotificationModel notificationModel, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(N_DATE, "" + notificationModel.getDate());
        values.put(N_HEAD, "" + notificationModel.getHead());
        values.put(N_IMAGE, "" + notificationModel.getImgname());
        values.put(N_NAME, "" + notificationModel.getName());
        values.put(N_ID, "" + notificationModel.getNid());
        values.put(N_URL, "" + notificationModel.getUrl());
        values.put(N_VIEW, "" + notificationModel.getView());

        // Inserting Row
        db.insert(TABLE_NOTIFICATION, null, values);

        db.close(); // Closing database connection
    }
////////////////////////////////////////////////////////////////////////////////////////////////////


    /////////////////////Getting All Contacts//////////////
    public ArrayList<NotificationModel> getAllNotification() {
        ArrayList<NotificationModel> notificationModels = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NOTIFICATION;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NotificationModel notificationModel = new NotificationModel();
                notificationModel.setDate(cursor.getString(0));
                notificationModel.setHead(cursor.getString(1));
                notificationModel.setImgname(cursor.getString(2));
                notificationModel.setName(cursor.getString(3));
                notificationModel.setNid(cursor.getString(4));
                notificationModel.setUrl(cursor.getString(5));
                notificationModel.setView(cursor.getString(6));

                notificationModels.add(notificationModel);
            } while (cursor.moveToNext());

        }

        // return contact list
        return notificationModels;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);


        // Create tables again
        onCreate(db);
    }
}