package com.example.sainisagar.project8;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sainisagar.project8.data.Contract;


/**
 * Created by Saini Sagar on 2018-01-03.
 */

public class ItemCursorAdapter extends CursorAdapter {
    public ItemCursorAdapter(Context context, Cursor c , int flags) {
        super(context, c, flags);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }
    @Override
    public void bindView(final View view, final Context context, final Cursor cursor) {
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView price = (TextView) view.findViewById(R.id.price);
        TextView quantity = (TextView) view.findViewById(R.id.quantity);
        view.setTag(cursor.getPosition());
        name.setText(cursor.getString(cursor.getColumnIndexOrThrow(Contract.Entry.COLUMN_PRODUCT_NAME)));
        price.setText(cursor.getFloat(cursor.getColumnIndexOrThrow(Contract.Entry.COLUMN_PRICE)) + " ");
        quantity.setText(cursor.getInt(cursor.getColumnIndexOrThrow(Contract.Entry.COLUMN_QUANTITY)) + "");
        final Button order = (Button) view.findViewById(R.id.order_button);
        final long id = cursor.getLong(cursor.getColumnIndex(Contract.Entry._ID));
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) view.findViewById(R.id.quantity);
                int prevQuantity = Integer.valueOf(textView.getText().toString());
                prevQuantity = prevQuantity - 1;
                if(prevQuantity < 0) {
                    Toast.makeText(context, R.string.wrong_quant, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    textView.setText(prevQuantity + "");
                    Uri contentUri = ContentUris.withAppendedId(Contract.Entry.CONTENT_URI, id);
                    Log.d("Ollivanders", contentUri.toString());
                    ContentValues values = new ContentValues();
                    String pname = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_PRODUCT_NAME));
                    double pprice = cursor.getDouble(cursor.getColumnIndex(Contract.Entry.COLUMN_PRICE));
                    String sname = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_SUPPLIER_NAME));
                    String smail = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_SUPPLIER_EMAIL));
                    String sphone = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_SUPPLIER_PHONE));

                    values.put(Contract.Entry.COLUMN_PRODUCT_NAME, pname);
                    values.put(Contract.Entry.COLUMN_PRICE, pprice);
                    values.put(Contract.Entry.COLUMN_QUANTITY, prevQuantity);
                    values.put(Contract.Entry.COLUMN_SUPPLIER_NAME, sname);
                    values.put(Contract.Entry.COLUMN_SUPPLIER_EMAIL, smail);
                    values.put(Contract.Entry.COLUMN_SUPPLIER_PHONE, sphone);

                    context.getContentResolver().update(contentUri, values, null, null);
                }
            }
        });
    }
}
