package com.example.sainisagar.project8;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sainisagar.project8.data.Contract.Entry;
import com.example.sainisagar.project8.data.DbHelper;


public class EditorActivity extends AppCompatActivity {
    private EditText NameEditText;
    private EditText PriceEditText;
    private EditText QuantityEditText;
    private EditText SnameEditText;
    private EditText SemailEditText;
    private EditText SphoneEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        NameEditText = (EditText) findViewById(R.id.edit_prod_name);
        PriceEditText = (EditText) findViewById(R.id.edit_prod_price);
        QuantityEditText = (EditText) findViewById(R.id.edit_prod_quant);
        SnameEditText = (EditText) findViewById(R.id.edit_prod_sname);
        SemailEditText = (EditText) findViewById(R.id.edit_prod_smail);
        SphoneEditText = (EditText) findViewById(R.id.edit_prod_sphone);
    }

     private void insertPet() {
        String nameString = NameEditText.getText().toString().trim();
        String breedString = PriceEditText.getText().toString().trim();
        String weightString = QuantityEditText.getText().toString().trim();
         String suppliername = SnameEditText.getText().toString().trim();
         String suppliermail = SemailEditText.getText().toString().trim();
         String supplierphone = SphoneEditText.getText().toString().trim();
        int weight = Integer.parseInt(weightString);

        DbHelper mDbHelper = new DbHelper(this);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Entry.COLUMN_PRODUCT_NAME, nameString);
        values.put(Entry.COLUMN_PRICE, breedString);
        values.put(Entry.COLUMN_QUANTITY, weight);
         values.put(Entry.COLUMN_SUPPLIER_NAME, suppliername);
         values.put(Entry.COLUMN_SUPPLIER_EMAIL, suppliermail);
         values.put(Entry.COLUMN_SUPPLIER_PHONE, supplierphone);

        long newRowId = db.insert(Entry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving pet", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Pet saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
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
                insertPet();
                finish();
                return true;
            case R.id.action_delete:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
