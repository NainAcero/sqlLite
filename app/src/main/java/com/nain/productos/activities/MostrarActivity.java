package com.nain.productos.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nain.productos.R;
import com.nain.productos.database.DatabaseHelper;

import java.util.ArrayList;

public class MostrarActivity extends AppCompatActivity {

    private ListView listview;
    private ArrayList<String> names;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        listview = (ListView) findViewById(R.id.listview);
        ArrayList <String> productos = new ArrayList<>();

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Cursor fila  = databaseHelper.getProductCursor();

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Lista de Productos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if(fila.moveToFirst()){
            do{
                productos.add(fila.getString(1) + " - " + fila.getString(2));
            }while(fila.moveToNext());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, productos);
        listview.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}