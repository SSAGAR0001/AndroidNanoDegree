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
    private static final String DATABASE_NAME = "shelter.db";
    private static final int DATABASE_VERSION = 1;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_INVENTORY_TABLE = "CREATE TABLE " + Entry.TABLE_NAME + " (" +
                Entry._ID + " INTEGER PRIMARY KEY," +
                Entry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, " +
                Entry.COLUMN_QUANTITY + " INTEGER NOT NULL, " +
                Entry.COLUMN_PRICE + " INTEGER NOT NULL, " +
                Entry.COLUMN_IMAGE + " BLOB NOT NULL, " +
                Entry.COLUMN_SUPPLIER_NAME + " TEXT, " +
                Entry.COLUMN_SUPPLIER_EMAIL + " TEXT, " +
                Entry.COLUMN_SUPPLIER_PHONE + " TEXT)";
        sqLiteDatabase.execSQL(SQL_CREATE_INVENTORY_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version) {
        db.execSQL("DROP TABLE IF EXISTS " + Entry.TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String productName, int quantity, int price,byte[] image, String s_name, String s_email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Entry.COLUMN_PRODUCT_NAME, productName);
        contentValues.put(Entry.COLUMN_QUANTITY, quantity);
        contentValues.put(Entry.COLUMN_PRICE, price);
        contentValues.put(Entry.COLUMN_IMAGE, image);
        contentValues.put(Entry.COLUMN_SUPPLIER_NAME, s_name);
        contentValues.put(Entry.COLUMN_SUPPLIER_EMAIL, s_email);
        contentValues.put(Entry.COLUMN_SUPPLIER_PHONE, phone);
        db.insert(Entry.TABLE_NAME, null, contentValues);
        return true;
    }
    public Cursor getData(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * from " + Contract.Entry.TABLE_NAME +
                " WHERE name=\"" + name + "\"", null);
        return res;
    }
    public int deleteEntries() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Contract.Entry.TABLE_NAME, null, null);
    }
    public boolean deleteData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Contract.Entry.TABLE_NAME, "name=?", new String[]{name}) > 0;
    }
    public void updateData(String name, int quantity, int change) {
        SQLiteDatabase db = this.getWritableDatabase();
        String strSQL = "UPDATE " + Contract.Entry.TABLE_NAME + " SET quantity = "
                + (quantity + change) + " WHERE name = \"" + name + "\"";
        db.execSQL(strSQL);
    }
    public ArrayList<String> getAllData() {
        ArrayList<String> productList = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor getList = db.rawQuery("SELECT * FROM " + Contract.Entry.TABLE_NAME, null);
        getList.moveToFirst();
        while (getList.isAfterLast() == false) {
            String productName = getList.getString(getList.getColumnIndex(Contract.Entry.COLUMN_PRODUCT_NAME));
            int quantity = getList.getInt(getList.getColumnIndex(Contract.Entry.COLUMN_QUANTITY));
            int price = getList.getInt(getList.getColumnIndex(Contract.Entry.COLUMN_PRICE));
            String image = getList.getString(getList.getColumnIndex(Entry.COLUMN_IMAGE));
            String s_name = getList.getString(getList.getColumnIndex(Entry.COLUMN_SUPPLIER_NAME));
            String s_email = getList.getString(getList.getColumnIndex(Entry.COLUMN_SUPPLIER_EMAIL));
            String s_phone = getList.getString(getList.getColumnIndex(Entry.COLUMN_SUPPLIER_PHONE));
            productList.add(productName + "\n" + "Quantity: " + quantity + "\n" + "Price: $" + price +
                "Image: " + image + "Supplier Name: " + s_name + "Supplier e-mail: " + s_email +
                "Suppllier Contact: " + s_phone);
            getList.moveToNext();
        }
        return productList;
    }
}
