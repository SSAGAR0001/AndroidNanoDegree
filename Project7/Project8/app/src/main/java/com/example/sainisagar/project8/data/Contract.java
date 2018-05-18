package com.example.sainisagar.project8.data;

import android.provider.BaseColumns;

/**
 * Created by Saini Sagar on 2017-12-29.
 */

public final class Contract {
    private Contract() {}
    public static final class Entry implements BaseColumns {
        public static final String TABLE_NAME = "INVENTORY";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_IMAGE = "Image";
        public static final String COLUMN_SUPPLIER_NAME = "s_name";
        public static final String COLUMN_SUPPLIER_EMAIL = "s_email";
        public static final String COLUMN_SUPPLIER_PHONE = "s_phone";
    }
}
