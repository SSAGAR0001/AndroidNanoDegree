package com.example.sainisagar.project8;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;


import com.example.sainisagar.project8.data.DbHelper;
import com.example.sainisagar.project8.data.Contract.Entry;
public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int ITEM_LOADER = 0;
    ItemCursorAdapter cursorAdapter;
    Cursor cursor;
    DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        dbHelper = new DbHelper(this);
        ListView itemListView = (ListView) findViewById(R.id.list);
        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        cursorAdapter = new ItemCursorAdapter(this, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        itemListView.setAdapter(cursorAdapter);
        View emptyView = findViewById(R.id.empty_view);
        itemListView.setEmptyView(emptyView);

        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                Uri currentItemUri = ContentUris.withAppendedId(Entry.CONTENT_URI, id);
                intent.setData(currentItemUri);
                startActivity(intent);
            }
        });
        getLoaderManager().initLoader(ITEM_LOADER, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader    (int id, Bundle args) {
        String[] projection = {
                Entry._ID,
                Entry.COLUMN_PRODUCT_NAME,
                Entry.COLUMN_PRICE,
                Entry.COLUMN_QUANTITY,
                Entry.COLUMN_SUPPLIER_NAME,
                Entry.COLUMN_SUPPLIER_EMAIL,
                Entry.COLUMN_SUPPLIER_PHONE
        };
        return new CursorLoader(this, Entry.CONTENT_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        cursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }
}
