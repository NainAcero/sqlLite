package com.nain.productos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.nain.productos.models.Product;
import com.nain.productos.table.ProductTable;

import java.util.List;

import static java.util.Arrays.asList;

public class DatabaseHelper extends SQLiteOpenHelper{
    public DatabaseHelper(Context context) {
        super(context, "Retail", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ProductTable.CREATE_QUERY);
        seedProducts(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int prevVersion, int newVersion) {
        sqLiteDatabase.execSQL(ProductTable.DROP_QUERY);
        sqLiteDatabase.execSQL(ProductTable.CREATE_QUERY);
    }

    private void seedProducts(SQLiteDatabase sqLiteDatabase){
        List<Product> products = asList(
                new Product("T.V", 5000),
                new Product("A.C", 15000),
                new Product("Washing Machine", 10000),
                new Product("Refrigerator", 13000),
                new Product("Microwave", 4000));

        for (Product product : products) {
            ContentValues values = new ContentValues();
            values.put(ProductTable.NAME, product.getName());
            values.put(ProductTable.PRICE, product.getPrice());
            sqLiteDatabase.insert(ProductTable.TABLE_NAME, null, values);
        }
        // DIRECTA
        ContentValues content = new ContentValues();
        content.put(ProductTable.NAME,"TV LG");
        content.put(ProductTable.PRICE, 2500);
        sqLiteDatabase.insert(ProductTable.TABLE_NAME, null, content);
        //
        Product valor = new Product("TV SAMSUNG",100);
        ContentValues values2 = new ContentValues();
        values2.put(ProductTable.NAME, valor.getName());
        values2.put(ProductTable.PRICE, valor.getPrice());
        sqLiteDatabase.insert(ProductTable.TABLE_NAME, null, values2);
    }

    public Cursor getProductCursor() {
        return this.getWritableDatabase().rawQuery(ProductTable.SElECT_QUERY, null);
    }
}
