package com.example.sqlexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME ="COUNTRY";
    public static final String _ID ="_id";
    public static final String SUBJECT ="subject";
    public static final String DESC ="description";
     static final String DB_NAME ="COUNTRY.DB";
     static final int DB_VERSION =2;
    private static String CREATE_TABLE =  " create table "+ TABLE_NAME +
            "("+ _ID +" INTEGER PRIMARY KEY AUTOINCREMENT , "+ SUBJECT +" TEXT NOT NULL , " + DESC + " TEXT);";


    public DatabaseHelper(@Nullable Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " +TABLE_NAME);
    onCreate(sqLiteDatabase);
    }
}
