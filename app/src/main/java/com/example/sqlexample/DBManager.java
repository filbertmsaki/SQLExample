package com.example.sqlexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
   private DatabaseHelper dbHelper;
   private Context context;
   private SQLiteDatabase database;

   public  DBManager(Context cont){
       context = cont;
   }

   public DBManager open() throws SQLException {
       dbHelper = new DatabaseHelper(context);
       database = dbHelper.getWritableDatabase();
       return this;
   }
   public void close(){
       dbHelper.close();
   }
   public void insert (String name, String desc){
       ContentValues values = new ContentValues();
       values.put(DatabaseHelper.SUBJECT, name);
       values.put(DatabaseHelper.DESC,desc);
       database.insert(DatabaseHelper.TABLE_NAME,null,values);
   }

   public Cursor fetch(){
       String[] columns ={DatabaseHelper._ID,DatabaseHelper.SUBJECT,DatabaseHelper.DESC};
       Cursor cursor = database.query(DatabaseHelper.TABLE_NAME,columns,
               null, null, null, null, null);
       if (cursor!= null){
           cursor.moveToFirst();
       }
       return cursor;
   }

   public int update(long _id, String name, String desc){
       ContentValues values = new ContentValues();
       values.put(DatabaseHelper.SUBJECT, name);
       values.put(DatabaseHelper.DESC,desc);
       int i = database.update(DatabaseHelper.TABLE_NAME,values,DatabaseHelper._ID +" = "+ _id,null);
       return i;
   }
   public void delete(long _id){
       database.delete(DatabaseHelper.TABLE_NAME,DatabaseHelper._ID + "=" + _id,null);
   }

}
