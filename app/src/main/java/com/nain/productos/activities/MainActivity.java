package com.nain.productos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nain.productos.R;
import com.nain.productos.database.DatabaseHelper;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextPrecio;
    Button mButtonRegister;
    AlertDialog mDialog;
    TextView textViewProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.txtInputNombre);
        editTextPrecio = findViewById(R.id.txtInputPrecio);
        mButtonRegister = findViewById(R.id.btnRegisterSave);
        textViewProductos = findViewById(R.id.textViewProductos);

        mDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Espere un momento")
                .setCancelable(false).build();

        textViewProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MostrarActivity.class);
                startActivity(intent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }

    private void register(){
        String nombre = editTextName.getText().toString();
        int precio = Integer.parseInt(editTextPrecio.getText().toString());
        if (!nombre.isEmpty()) {
            mDialog.show();
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            SQLiteDatabase db = databaseHelper.getWritableDatabase();
            if (db != null) {
                ContentValues cv = new ContentValues();
                cv.put("name", nombre);
                cv.put("price", precio);
                db.insert("products", null, cv);
            }
            mDialog.dismiss();
        }
    }
}