package com.example.sainisagar.project8;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
//import android.content.Loader;
import android.content.CursorLoader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextWatcher;
import android.app.LoaderManager;


import com.example.sainisagar.project8.data.Contract;
import com.example.sainisagar.project8.data.Contract.Entry;

import java.io.Serializable;
import java.util.jar.Attributes;


public class EditorActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<Cursor>{
    private EditText NameEditText;
    private EditText PriceEditText;
    private TextView QuantityEditText;
    private EditText SnameEditText;
    private EditText SemailEditText;
    private EditText SphoneEditText;
    private Button order;
    Uri currentItemUri;
    private static final int ITEM_LOADER = 1;
    public boolean itemChanged = false;
    private View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            itemChanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Button plus = (Button)findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView quan = (TextView) findViewById(R.id.quantity);
                int val = Integer.valueOf(quan.getText().toString());
                val++;
                quan.setText(val + "");
            }
        });

        Button minus = (Button)findViewById(R.id.minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView quan = (TextView) findViewById(R.id.quantity);
                int val = Integer.valueOf(quan.getText().toString());
                val--;
                if(val < 0){
                    Toast.makeText(getBaseContext(), R.string.wrong_quant,
                            Toast.LENGTH_LONG).show();
                    return;
                }
                quan.setText(val + "");
            }
        });

        NameEditText = (EditText) findViewById(R.id.product_edit_name);
        PriceEditText = (EditText) findViewById(R.id.product_edit_price);
        QuantityEditText = (TextView) findViewById(R.id.quantity);
        SnameEditText = (EditText) findViewById(R.id.supplier_edit_name);
        SemailEditText = (EditText) findViewById(R.id.supplier_edit_email);
        SphoneEditText = (EditText) findViewById(R.id.supplier_edit_phone);

        NameEditText.setOnTouchListener(listener);
        PriceEditText.setOnTouchListener(listener);
        QuantityEditText.setOnTouchListener(listener);
        SnameEditText.setOnTouchListener(listener);
        SemailEditText.setOnTouchListener(listener);
        SphoneEditText.setOnTouchListener(listener);

        currentItemUri = getIntent().getData();

        if(currentItemUri == null) {
            setTitle(getString(R.string.editor_activity_title_new_pet));
            invalidateOptionsMenu();
        }
        else {
            setTitle(getString(R.string.editor_activity_title_edit_item));
            order = (Button)findViewById(R.id.order);
            order.setVisibility(View.VISIBLE);
            getLoaderManager().initLoader(ITEM_LOADER, null, this);
        }
    }
    @Override
    public android.content.Loader onCreateLoader(int i, Bundle args) {
        String selection = Entry._ID + "=?";
        String []selectionArgs = new String[]{String.valueOf(ContentUris.parseId(currentItemUri))};
        String []projection = {
                Entry.COLUMN_PRODUCT_NAME,
                Entry.COLUMN_PRICE,
                Entry.COLUMN_QUANTITY,
                Entry.COLUMN_SUPPLIER_NAME,
                Entry.COLUMN_SUPPLIER_EMAIL,
                Entry.COLUMN_SUPPLIER_PHONE
        };
        return new CursorLoader(this, currentItemUri, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> Loader, Cursor cursor) {
        final StringBuilder orderSummary = new StringBuilder();
        if (cursor.moveToFirst()) {
            NameEditText = (EditText)findViewById(R.id.product_edit_name);
            String productname = cursor.getString(cursor.getColumnIndex
                    (Entry.COLUMN_PRODUCT_NAME));
            NameEditText.setText(productname);
            orderSummary.append(productname + "\n");

            QuantityEditText = (TextView) findViewById(R.id.quantity);
            String quantity = cursor.getInt(cursor.getColumnIndex
                    (Entry.COLUMN_QUANTITY))+"";
            QuantityEditText.setText(quantity);
            orderSummary.append(quantity + "\n");

            PriceEditText = (EditText)findViewById(R.id.product_edit_price);
            String productPrice = cursor.getDouble(cursor.getColumnIndex
                    (Entry.COLUMN_PRICE))+"";
            PriceEditText.setText(productPrice);
            orderSummary.append(productPrice + "\n");

            SnameEditText = (EditText)findViewById(R.id.supplier_edit_name);
            String supplierName = cursor.getString(cursor.getColumnIndex
                    (Entry.COLUMN_SUPPLIER_NAME));
            SnameEditText.setText(supplierName);
            orderSummary.append(supplierName + "\n");

            SemailEditText = (EditText)findViewById(R.id.supplier_edit_email);
            SemailEditText.setText(cursor.getString(cursor.getColumnIndex
                    (Entry.COLUMN_SUPPLIER_EMAIL)));


            SphoneEditText = (EditText)findViewById(R.id.supplier_edit_phone);
            String supplierPhone = cursor.getString(cursor.getColumnIndex
                    (Entry.COLUMN_SUPPLIER_PHONE));
            SphoneEditText.setText(supplierPhone);
            orderSummary.append(supplierPhone);
        }
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, "ollivanders@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Every Wand chooses the wizard itself!");
                intent.putExtra(Intent.EXTRA_TEXT, (Serializable) orderSummary );
                //Toast.makeText(this, getString(R.string.orderSuccess), Toast.LENGTH_LONG).show();
                startActivity(intent.createChooser(intent, "Send e-mail"));
            }
        });
    }

    @Override
    public void onLoaderReset(android.content.Loader loader){
        NameEditText = (EditText)findViewById(R.id.product_edit_name);
        NameEditText.setText("");

        QuantityEditText = (TextView) findViewById(R.id.quantity);
        QuantityEditText.setText("0");

        PriceEditText = (EditText)findViewById(R.id.product_edit_price);
        PriceEditText.setText("");

        SnameEditText = (EditText)findViewById(R.id.supplier_edit_name);
        SnameEditText.setText("");

        SemailEditText = (EditText)findViewById(R.id.supplier_edit_email);
        SemailEditText.setText("");

        SphoneEditText = (EditText)findViewById(R.id.supplier_edit_phone);
        SphoneEditText.setText("");
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if(currentItemUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveItem();
                return true;
            case R.id.action_delete:
                DialogInterface.OnClickListener deleteButton = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteItem();
                        finish();
                    }
                };
                deleteWarning(deleteButton);
                return true;
            case android.R.id.home:
                if(!itemChanged) {
                    NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    return true;
                }
                DialogInterface.OnClickListener discardButton = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    }
                };
                showUnsavedChangesDialog(discardButton);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(!itemChanged) {
            super.onBackPressed();
            return;
        }
        DialogInterface.OnClickListener discardButton = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        };
        showUnsavedChangesDialog(discardButton);
    }

    private void showUnsavedChangesDialog(DialogInterface.OnClickListener discardButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButton);
        builder.setNegativeButton(R.string.keep_edit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteWarning(DialogInterface.OnClickListener discardButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, discardButton);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteItem() {
        if(currentItemUri != null) {
            int deletedRows = getContentResolver().delete(currentItemUri, null, null);
            if(deletedRows == 0) {
                Toast.makeText(this, getString(R.string.editor_delete_pet_failed), Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, getString(R.string.editor_delete_pet_successful), Toast.LENGTH_SHORT).show();
            }
        }
    }



    private static final double ZERO = 0;
    private void saveItem() {
        try {
            ContentValues values = new ContentValues();
            NameEditText = (EditText)findViewById(R.id.product_edit_name);
            String productName = NameEditText.getText().toString();

            PriceEditText = (EditText) findViewById(R.id.product_edit_price);
            Double productPrice = ZERO;

            if(!PriceEditText.getText().toString().matches("")) {
                productPrice = Double.valueOf(PriceEditText.getText().toString());
            }
            QuantityEditText = (TextView) findViewById(R.id.quantity);
            Integer productQuantity = Integer.valueOf(QuantityEditText.getText().toString());
            SnameEditText = (EditText)findViewById(R.id.supplier_edit_name);
            String sname = SnameEditText.getText().toString();
            SemailEditText = (EditText)findViewById(R.id.supplier_edit_email);
            String smail = SemailEditText.getText().toString();
            SphoneEditText = (EditText)findViewById(R.id.supplier_edit_phone);
            String sphone = SphoneEditText.getText().toString();

            values.put(Contract.Entry.COLUMN_PRODUCT_NAME, productName);
            values.put(Contract.Entry.COLUMN_PRICE, productPrice);
            values.put(Contract.Entry.COLUMN_QUANTITY, productQuantity);
            values.put(Contract.Entry.COLUMN_SUPPLIER_NAME, sname);
            values.put(Contract.Entry.COLUMN_SUPPLIER_EMAIL, smail);
            values.put(Contract.Entry.COLUMN_SUPPLIER_PHONE, sphone);

            if(currentItemUri == null) {
                Uri newUri = getContentResolver().insert(Entry.CONTENT_URI, values);
                if(newUri == null) {
                    Toast.makeText(this, getString(R.string.editor_insert_failed), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, getString(R.string.editor_insert_successful), Toast.LENGTH_SHORT).show();
                }
            }else {
                int affectedRows = getContentResolver().update(currentItemUri, values, null, null);
                if (affectedRows == 0) {
                    Toast.makeText(this, getString(R.string.editor_update_failed), Toast.LENGTH_SHORT).show();
                } else {
                    // Otherwise, the update was successful and we can display a toast.
                    Toast.makeText(this, getString(R.string.editor_update_successful), Toast.LENGTH_SHORT).show();
                }

            }
            finish();
        }catch (Exception e) {
            Toast.makeText(this, R.string.error_check, Toast.LENGTH_SHORT).show();
        }
    }
}
