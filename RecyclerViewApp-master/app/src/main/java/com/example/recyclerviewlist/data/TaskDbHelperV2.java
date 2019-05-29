package com.example.recyclerviewlist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.recyclerviewlist.data.TaskContractV2.TaskEntry;

public class TaskDbHelperV2 extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TasksDb.db";

    public TaskDbHelperV2(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE =
                "CREATE TABLE " + TaskEntry.TABLE_NAME + " ("
                + TaskEntry._ID +               " INTEGER PRIMARY KEY,"
                + TaskEntry.COLUMN_DESCRIPTION  + " TEXT NOT NULL,"
                + TaskEntry.COLUMN_PRIORITY     + " TEXT NOT NULL)";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TaskEntry.TABLE_NAME;

        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
