package com.example.sainisagar.project8.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.sainisagar.project8.data.Contract.Entry;

import java.util.ArrayList;

/**
 * Created by Saini Sagar on 2017-12-29.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = DbHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "WANDSTORE";
    private static final int DATABASE_VERSION = 1;
    private static String SQL_CREATE_INVENTORY_TABLE = "CREATE TABLE " + Entry.TABLE_NAME + " (" +
            Entry._ID + " INTEGER PRIMARY KEY," +
            Entry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, " +
            Entry.COLUMN_PRICE + " INTEGER NOT NULL, " +
            Entry.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0, " +
            Entry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, " +
            Entry.COLUMN_SUPPLIER_EMAIL + " TEXT, " +
            Entry.COLUMN_SUPPLIER_PHONE + " TEXT)";
    private static String DEL_QUERY = "DROP TABLE IF EXISTS";
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_INVENTORY_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version) {

    }
}
