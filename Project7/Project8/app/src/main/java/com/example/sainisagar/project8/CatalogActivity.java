package com.example.sainisagar.project8;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.sainisagar.project8.data.DbHelper;
import com.example.sainisagar.project8.data.Contract.Entry;
public class CatalogActivity extends AppCompatActivity {
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        dbHelper = new DbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                Entry._ID,
                Entry.COLUMN_PRODUCT_NAME,
                Entry.COLUMN_PRICE,
                Entry.COLUMN_QUANTITY,
                Entry.COLUMN_IMAGE,
                Entry.COLUMN_SUPPLIER_NAME,
                Entry.COLUMN_SUPPLIER_EMAIL,
                Entry.COLUMN_SUPPLIER_PHONE }   ;

        // Perform a query on the pets table
        Cursor cursor = c_read(db, projection);                   // The sort order

        TextView displayView = (TextView) findViewById(R.id.text_view_pet);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(Entry._ID + " - " +
                    Entry.COLUMN_PRODUCT_NAME + " - " +
                    Entry.COLUMN_PRICE + " - " +
                    Entry.COLUMN_QUANTITY + " - " +
                    Entry.COLUMN_IMAGE + " - " +
                    Entry.COLUMN_SUPPLIER_NAME + " - " +
                    Entry.COLUMN_SUPPLIER_EMAIL + " - " +
                    Entry.COLUMN_SUPPLIER_PHONE + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(Entry._ID);
            int nameColumnIndex = cursor.getColumnIndex(Entry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(Entry.COLUMN_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(Entry.COLUMN_QUANTITY);
            int imageColumnIndex = cursor.getColumnIndex(Entry.COLUMN_IMAGE);
            int snameColumnIndex = cursor.getColumnIndex(Entry.COLUMN_SUPPLIER_NAME);
            int semailColumnIndex = cursor.getColumnIndex(Entry.COLUMN_SUPPLIER_EMAIL);
            int sphoneColumnIndex = cursor.getColumnIndex(Entry.COLUMN_SUPPLIER_PHONE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentImage = cursor.getString(imageColumnIndex);
                String currentsname = cursor.getString(snameColumnIndex);
                String currentsemail = cursor.getString(semailColumnIndex);
                String currentsphone = cursor.getString(sphoneColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentPrice + " - " +
                        currentQuantity + " - " +
                        currentImage + " - " +
                        currentsname + " - " +
                        currentsemail + " - " +
                        currentsphone));
            }
        } finally {
            cursor.close();
        }
    }
    private Cursor c_read(SQLiteDatabase db, String[] projection) {
        Cursor cursor = db.query(
                Entry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);
        return cursor;
    }
    private void insertPet() {
        // Gets the database in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(Entry.COLUMN_PRODUCT_NAME, "Wand");
        values.put(Entry.COLUMN_PRICE, "$100");
        values.put(Entry.COLUMN_QUANTITY, "10");
        values.put(Entry.COLUMN_IMAGE, android.R.drawable.ic_dialog_alert);
        values.put(Entry.COLUMN_SUPPLIER_NAME, "Mr. Oliwander");
        values.put(Entry.COLUMN_SUPPLIER_EMAIL, "oliwanderbill@oliwanderwands.com");
        values.put(Entry.COLUMN_SUPPLIER_PHONE, "8978945612");

        // Insert a new row for Toto in the database, returning the ID of that new row.
        // The first argument for db.insert() is the pets table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Toto.
        long newRowId = db.insert(Entry.TABLE_NAME, null, values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
