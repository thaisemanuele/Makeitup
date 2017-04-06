package com.natura.teste.thaissantos.makeitup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "create table catalog " +
                    "(id text primary key, name text, color_r integer, " +
                    "color_g integer, color_b integer, category text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS catalog");
        onCreate(db);
    }

    public boolean insertProduct (String id, String name, int color_r, int color_g, int color_b, String category) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("color_r", color_r);
        contentValues.put("color_g", color_g);
        contentValues.put("color_b", color_b);
        contentValues.put("category", category);

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

    public void populate(){

        insertProduct("PO_COMP_AQ_40", "Pó Compacto Aquarela - Bege Claro",    236,199,155,"PO");
        insertProduct("PO_COMP_AQ_42", "Pó Compacto Aquarela - Bege Rosado",   249,196,164,"PO");
        insertProduct("PO_COMP_AQ_44", "Pó Compacto Aquarela - Bege Medio",    229,186,135,"PO");
        insertProduct("PO_COMP_AQ_46", "Pó Compacto Aquarela - Bege Castanho", 202,144,104,"PO");
        insertProduct("PO_COMP_AQ_48", "Pó Compacto Aquarela - Marrom Claro",  167,119,105,"PO");
        insertProduct("PO_COMP_AQ_50", "Pó Compacto Aquarela - Marrom Escuro", 158,112,89, "PO");
        insertProduct("PO_COMP_FAC_MED", "Pó Compacto Faces - Médio", 249,145,110, "PO");
        insertProduct("PO_COMP_FAC_CAS", "Pó Compacto Faces - Castanho", 207,126,96, "PO");
        insertProduct("PO_COMP_FAC_CLA", "Pó Compacto Faces - Claro", 253,201,154, "PO");
        insertProduct("BAT_MET_AQ_COB", "Batom Metalizado FPS8 Aquarela - Cobre", 109,45,16, "BAT");
        insertProduct("BAT_MET_AQ_UVA", "Batom Metalizado FPS8 Aquarela - Uva", 109,45,16, "BAT");
        insertProduct("BAT_MET_AQ_COB", "Batom Metalizado FPS8 Aquarela - Cobre", 79,51,66, "BAT");

    }
}
