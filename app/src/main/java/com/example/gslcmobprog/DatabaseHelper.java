package com.example.gslcmobprog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String table_name = "cart";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "cartDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + table_name + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "image INTEGER NOT NULL" +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + table_name;
        db.execSQL(query);
        onCreate(db);
    }

    public boolean insertCartItem(String name, int image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("image", image);
        long result = db.insert(table_name, null, values);
        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + table_name;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public void clearCart() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM cart";
        db.execSQL(query);
        db.close();
    }
}
