package com.natura.teste.thaissantos.makeitup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by thais.santos on 05/04/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "appDatabase.db";
    private static String CATALOG_TABLE_NAME = "catalog";
    private static String CATALOG_COLUMN_ID = "id";
    private static String CATALOG_COLUMN_NAME = "name";
    private static String CATALOG_COLUMN_COLOR_R = "color_r";
    private static String CATALOG_COLUMN_COLOR_G = "color_g";
    private static String CATALOG_COLUMN_COLOR_B = "color_b";
    private static String CATALOG_COLUMN_CATEGORY = "category";
    private static String CATALOG_COLUMN_URL = "url_image";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "create table catalog " +
                    "(id text primary key, name text, color_r integer, " +
                    "color_g integer, color_b integer, category text, url_image text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS catalog");
        onCreate(db);
    }

    public boolean insertProduct (Product product) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", product.getId());
        contentValues.put("name", product.getName());
        contentValues.put("color_r", product.getColor_r());
        contentValues.put("color_g", product.getColor_g());
        contentValues.put("color_b", product.getColor_b());
        contentValues.put("category", product.getCategory().getDescription());
        contentValues.put("url_image", product.getUrl_image());

        db.insert("catalog", null, contentValues);

        return true;
    }

    public Cursor getAllProducts() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result =  db.rawQuery( "select * from catalog;", null );
        return result;
    }

    public Cursor getProducts(int r, int g, int b, int range) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result =  db.rawQuery( "select * from catalog where (" +
                "color_r BETWEEN "+(r-range)+" AND " +(r+range) +") AND ("+
                "color_g BETWEEN "+(g-range)+" AND " +(g+range) +") AND ("
                        +"color_b BETWEEN "+(b-range)+" AND " +(b+range)+");"
                , null );
        return result;
    }

    public void populate(List<Product> products) {

        for (Product product : products) {
            insertProduct(product);
        }
    }

    public List<Product> productFactory(){

        List<Product> products = new LinkedList<Product>();

        products.add( new Product("PO_COMP_AQ_40", "Pó Compacto Aquarela - Bege Claro",
                236,199,155,Category.PO,"http://www.natura.com.br/sites/default/files/products/38729-1.jpg"));

        products.add( new Product("PO_COMP_AQ_42", "Pó Compacto Aquarela - Bege Rosado",
                249,196,164,Category.PO,"http://www.natura.com.br/sites/default/files/products/38731-1.jpg"));

        products.add( new Product("PO_COMP_AQ_44", "Pó Compacto Aquarela - Bege Medio",
                229,186,135,Category.PO,"http://www.natura.com.br/sites/default/files/products/38730-1.jpg"));

        products.add( new Product("PO_COMP_AQ_46", "Pó Compacto Aquarela - Bege Castanho",
                202,144,104,Category.PO,"http://www.natura.com.br/sites/default/files/products/38733-1.jpg"));

        products.add( new Product("PO_COMP_AQ_48", "Pó Compacto Aquarela - Marrom Claro",
                167,119,105,Category.PO, "http://www.natura.com.br/sites/default/files/products/38732-1.jpg"));

        products.add( new Product("PO_COMP_AQ_50", "Pó Compacto Aquarela - Marrom Escuro",
                158,112,89, Category.PO, "http://www.natura.com.br/sites/default/files/products/38728-1.jpg"));

        products.add( new Product("PO_COMP_FAC_MED", "Pó Compacto Faces - Médio",
                249,145,110,Category.PO, "http://www.natura.com.br/sites/default/files/products/77644-0.jpg"));

        products.add( new Product("PO_COMP_FAC_CAS", "Pó Compacto Faces - Castanho",
                207,126,96, Category.PO, "http://www.natura.com.br/sites/default/files/products/77645-0.jpg"));

        products.add( new Product("PO_COMP_FAC_CLA", "Pó Compacto Faces - Claro",
                253,201,154,Category.PO, "http://www.natura.com.br/sites/default/files/products/77646-0.jpg"));

        products.add( new Product("BAT_MET_AQ_COB", "Batom Metalizado FPS8 Aquarela - Cobre",
                109,45,16, Category.BATM, "http://www.natura.com.br/sites/default/files/products/79012-0.jpg"));

        products.add( new Product("BAT_MET_AQ_UVA", "Batom Metalizado FPS8 Aquarela - Uva",
                109,45,16, Category.BATM, "http://www.natura.com.br/sites/default/files/products/79011-0.jpg"));

        products.add( new Product("BAT_MET_AQ_VIN", "Batom Metalizado FPS8 Aquarela - Vinho",
                140,33,61,  Category.BATM, "http://www.natura.com.br/sites/default/files/products/79010-0.jpg"));

        products.add( new Product("BAT_MET_AQ_GRFT", "Batom Metalizado FPS8 Aquarela - Grafite",
                56,63,71,  Category.BATM, "http://www.natura.com.br/sites/default/files/products/79009-0.jpg"));

        products.add( new Product("BAT_MET_AQ_VERM", "Batom Metalizado FPS8 Aquarela - Vermelho",
                142,33,43,  Category.BATM, "http://www.natura.com.br/sites/default/files/products/79008-0.jpg"));

        products.add( new Product("BAT_MET_AQ_PINK", "Batom Metalizado FPS8 Aquarela - Pink",
                175,72,120,  Category.BATM, "http://www.natura.com.br/sites/default/files/products/79007-0.jpg"));

        return products;

    }
}
