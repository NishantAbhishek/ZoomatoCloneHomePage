package com.example.zomatoclone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.android.gms.maps.Projection;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String CREATE_EVENTS_TABLE = "create table "+DBStructure.EventTableName+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
            DBStructure.EVENT+" TEXT,"+DBStructure.TIME+" TEXT, "+DBStructure.DATE+" TEXT, "+DBStructure.MONTH+" TEXT, "+
            DBStructure.YEAR+" TEXT)";

    private static final String DROP_EVENT_TABLE = "DROP TABLE IF EXISTS "+DBStructure.EventTableName;

    public DBOpenHelper(@Nullable Context context) {
        super(context, DBStructure.DB_NAME, null, DBStructure.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_EVENT_TABLE);
        onCreate(db);
    }

    public void SaveEvent(String event,String time,String date,String month,String year,SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBStructure.EVENT,event);
        contentValues.put(DBStructure.TIME,time);
        contentValues.put(DBStructure.DATE,date);
        contentValues.put(DBStructure.MONTH,month);
        contentValues.put(DBStructure.YEAR,year);
        database.insert(DBStructure.EventTableName,null,contentValues);
    }

    public Cursor ReadEvents(String data,SQLiteDatabase database){
        String[] projection = {DBStructure.EVENT,DBStructure.TIME,DBStructure.DATE,DBStructure.MONTH,DBStructure.YEAR};
        String selection = DBStructure.DATE+"=?";
        String[] selectionArgs = {data};
        return database.query(DBStructure.EventTableName, projection,selection,selectionArgs,null,null,null,null);
    }

    public Cursor ReadEventsPerMonth(String month,String year,SQLiteDatabase database){
        String[] projection = {DBStructure.EVENT,DBStructure.TIME,DBStructure.DATE,DBStructure.MONTH,DBStructure.YEAR};
        String selection = DBStructure.MONTH + "=? and "+DBStructure.YEAR + "=?";

        String[] selectionArgs = {month,year};

        return database.query(DBStructure.EventTableName, projection,selection,selectionArgs,null,null,null,null);

    }

}
