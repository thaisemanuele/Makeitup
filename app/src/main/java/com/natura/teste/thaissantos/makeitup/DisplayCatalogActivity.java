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

        Cursor products = dbhelper.getProducts(redvalue, greenvalue, bluevalue, 40);
        ArrayList<Product> productsList = new ArrayList<Product>();
        if(products.isAfterLast()){
            Toast.makeText(this, "Não Encontrado! Mostrando todas opções", Toast.LENGTH_LONG).show();
            products = dbhelper.getAllProducts();
        }
        while(products.moveToNext()){

            String id = products.getString(products.getColumnIndex("id"));
            String name = products.getString(products.getColumnIndex("name"));
            String color_desc = products.getString(products.getColumnIndex("colordesc"));

            int color_r = products.getInt(products.getColumnIndex("color_r"));
            int color_g = products.getInt(products.getColumnIndex("color_g"));
            int color_b = products.getInt(products.getColumnIndex("color_b"));

            Category category = Category.NOTFOUND.getByDescription(products.getString(products.getColumnIndex("category")));
            String url_image = products.getString(products.getColumnIndex("url_image"));

            Product product = new Product(id, name, color_desc, color_r, color_g, color_b, category, url_image);
            productsList.add(product);
        }


        ListView listView = (ListView) findViewById(R.id.productsList);
        ProductListAdapter arrayAdapter = new ProductListAdapter (
                this,
                productsList );

        listView.setAdapter(arrayAdapter);
    }

}
