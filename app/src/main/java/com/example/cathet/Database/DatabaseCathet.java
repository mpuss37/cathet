package com.example.cathet.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseCathet extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cathet.db";

    public final String table_category = "category";
    public final String col_name_category = "name_category";

    private Context context;
    private ContentValues contentValues;
    private static final int DATABASE_VERSION = 1;

    public DatabaseCathet(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table IF NOT EXISTS " + table_category + " (id_category integer primary key autoincrement," + col_name_category + " text)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}