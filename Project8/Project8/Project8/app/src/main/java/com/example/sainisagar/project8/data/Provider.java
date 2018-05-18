package com.example.sainisagar.project8.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaCodec;
import android.net.Uri;
import android.util.Log;
import android.util.Patterns;

import com.example.sainisagar.project8.data.Contract.Entry;

import com.example.sainisagar.project8.data.DbHelper;

import java.util.regex.Pattern;

/**
 * Created by Saini Sagar on 2018-01-03.
 */

public class Provider extends ContentProvider {
    public static final int ITEMS = 100;
    public static final int ITEM_ID = 101;
    private DbHelper dbHelper;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(Contract.CONTENT_AUTHORITY, Contract.PATH_ITEMS, ITEMS);
        sUriMatcher.addURI(Contract.CONTENT_AUTHORITY, Contract.PATH_ITEMS + "/#", ITEM_ID);
    }

    public static final String LOG_TAG = Provider.class.getSimpleName();
    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        int match = sUriMatcher.match(uri);
        switch(match) {
            case ITEMS:
                cursor = database.query(Entry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case ITEM_ID:
                selection = Contract.Entry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(Contract.Entry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Connot Query Unkonwn Uri" + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ITEMS:
                return insertItem(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertItem(Uri uri, ContentValues contentValues) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        validateData(contentValues);
        long id = database.insert(Contract.Entry.TABLE_NAME, null, contentValues);
        if(id == -1) {
            Log.e(LOG_TAG, "Insertion failed for" + uri );
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch(match) {
            case ITEMS:
                return updateItem(uri, contentValues, selection, selectionArgs);
            case ITEM_ID:
                selection = Entry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updateItem(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int updateItem(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        validateData(values);
        if (values.size() == 0) {
            return 0;
        }
        int rowsUpdated = database.update(Entry.TABLE_NAME, values, selection, selectionArgs);
        if(rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ITEMS:
                // Delete all rows that match the selection and selection args
                return database.delete(Entry.TABLE_NAME, selection, selectionArgs);
            case ITEM_ID:
                // Delete a single row given by the ID in the URI
                selection = Entry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                int rowsDeleted = database.delete(Entry.TABLE_NAME, selection, selectionArgs);
                if(rowsDeleted != 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rowsDeleted;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
            switch(match) {
                case ITEMS: return Entry.CONTENT_LIST_TYPE;
                case ITEM_ID: return Entry.CONTENT_ITEM_TYPE;
                default: throw new IllegalArgumentException("Unknown uri: " + uri + " with match: " + match);
            }
    }

    private void validateData(ContentValues contentValues) {
        String name = contentValues.getAsString(Entry.COLUMN_PRODUCT_NAME);
        if(name == null || name.matches("")) {
            throw new IllegalArgumentException("Name is required to add a new product!");
        }
        String s_name = contentValues.getAsString(Entry.COLUMN_SUPPLIER_NAME);
        if(s_name == null || s_name.matches("")) {
            throw new IllegalArgumentException("Supplier name is required to add a product!");
        }
        Double price = contentValues.getAsDouble(Entry.COLUMN_PRICE);
        if(price != null && price < 0) {
            throw new IllegalArgumentException("Please Enter a valid price for the product!");
        }
        Integer quantity = contentValues.getAsInteger(Entry.COLUMN_QUANTITY);
        if(quantity != null && quantity < 0) {
            throw new IllegalArgumentException("Please enter a valid number as quantity!");
        }
        String mail = contentValues.getAsString(Entry.COLUMN_SUPPLIER_EMAIL);
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        if(mail.matches("") || (!mail.matches("") && !pattern.matcher(mail).matches())) {
            throw new IllegalArgumentException("Please enter a valid e-mail address!");
        }
        String phone = contentValues.getAsString(Entry.COLUMN_SUPPLIER_PHONE);
        if(phone == null || phone.matches("")) {
            throw new IllegalArgumentException("Please enter a valid contact number");
        }
    }
}
