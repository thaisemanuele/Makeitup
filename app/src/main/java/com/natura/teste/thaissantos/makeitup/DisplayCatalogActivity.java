package com.natura.teste.thaissantos.makeitup;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayCatalogActivity extends AppCompatActivity {

    DataBaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_catalog);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbhelper = new DataBaseHelper(this);

        Bundle extras = getIntent().getExtras();

        int redvalue =   (int )extras.get("r");
        int greenvalue = (int )extras.get("g");
        int bluevalue =  (int )extras.get("b");

        Cursor products = dbhelper.getProducts(redvalue, greenvalue, bluevalue, 45);
        ArrayList<String> productsList = new ArrayList<String>();
        if(products.isAfterLast()){
            Toast.makeText(this, "Não Encontrado! Mostrando todas opções", Toast.LENGTH_LONG).show();
            products = dbhelper.getAllProducts();
        }
        while(products.moveToNext()){
            String id = products.getString(products.getColumnIndex("id"));
            String name = products.getString(products.getColumnIndex("name"));
            productsList.add(id+" "+name);
        }


        ListView listView = (ListView) findViewById(R.id.productsList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                productsList );

        listView.setAdapter(arrayAdapter);
    }

}
