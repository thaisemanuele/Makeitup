package com.natura.teste.thaissantos.makeitup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by thais.santos on 09/04/2017.
 */

public class ProductListAdapter extends ArrayAdapter<Product> {

    public ProductListAdapter(Context context, List<Product> list){
        super(context, 0, list);
    }

    @Override
    public View getView(int pos, View view, ViewGroup parentView){

        Product product = (Product) getItem(pos);

        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.product_adaptor, parentView, false);
        }

        ImageView image = (ImageView) view.findViewById(R.id.product_image);
        TextView imageName = (TextView) view.findViewById(R.id.product_name);
        TextView imageColorDesc = (TextView) view.findViewById(R.id.product_color);

        String name = product.getName();
        imageName.setText(name);

        String colorDesc = product.getColorDesc();
        imageColorDesc.setText(colorDesc);

        new LoadProductImage(product.getUrl_image(), image).execute();

        return view;
    }

}
