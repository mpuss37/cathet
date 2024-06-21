package com.example.cathet.Handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cathet.Database.DatabaseCathet;
import com.example.cathet.MainActivity;

import java.util.ArrayList;

public class CategoryHandler extends MainActivity {
    private static ContentValues contentValues;
    private static DatabaseCathet databaseCathet;
    private static SQLiteDatabase sqLiteDatabase;

    public CategoryHandler(Context context) {
        databaseCathet = new DatabaseCathet(context);
    }


    public void openWrite() {
        sqLiteDatabase = databaseCathet.getWritableDatabase();
    }

    public void openRead() {
        sqLiteDatabase = databaseCathet.getReadableDatabase();
    }

    public void close() {
        databaseCathet.close();
    }

    public static long insertDataPass(int id_category, String category) {
        contentValues = new ContentValues();
        contentValues.put("id_category", id_category);
        contentValues.put(databaseCathet.col_name_category, category);
        return sqLiteDatabase.insert(databaseCathet.table_category, null, contentValues);
    }

    public static long editDataPass(int id_category, String category) {
        contentValues = new ContentValues();
        String whereClause = category + " = ?";
        String[] whereArgs = {String.valueOf(category)};
        contentValues.put("id_category", id_category);
        contentValues.put(databaseCathet.col_name_category, category);
        return sqLiteDatabase.update(databaseCathet.table_category, contentValues, whereClause, whereArgs);
    }

    public int readData() {
        int id_data = -1;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + databaseCathet.table_category + " WHERE " + databasePass.col_website_username + " = '" + username + "'", null);
        if (cursor.moveToFirst()) {
            id_data = cursor.getInt(0);
        }
        cursor.close();
        return id_data;
    }

    public void deleteData(String username) {
        sqLiteDatabase.delete(databasePass.table_data, databasePass.col_website_username + " = '" + username + "'", null);
    }

    public int countData(int id_user) {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT id_data FROM " + databasePass.table_data + " where id_user = '" + id_user + "'", null);
        int jumlah = cursor.getCount();
        cursor.close();
        return jumlah;
    }

    public int checkIdData(String website, String username, String password) {
        int id_data = -1;
        String[] columns = {"id_data"};
        String selection = "name_website = ? and username = ? and password = ?";
        String[] selectionArgs = {website, username, password};
        Cursor cursor = sqLiteDatabase.query(databasePass.table_data, columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            id_data = cursor.getInt(cursor.getColumnIndexOrThrow("id_data"));
        }

        cursor.close();
        return id_data;
    }

    public boolean checkData(int id_user) {
        Cursor cursor = sqLiteDatabase.rawQuery("select id_data from " + databasePass.table_data + " where id_user = " + id_user, null);
        if (cursor.getCount() >= 4) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public ArrayList<DataModel> displayData(int id_user) {
        sqLiteDatabase = databasePass.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + databasePass.table_data + " where id_user = " + id_user, null);
        ArrayList<DataModel> modelArrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                modelArrayList.add(new DataModel(cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return modelArrayList;
    }

}
