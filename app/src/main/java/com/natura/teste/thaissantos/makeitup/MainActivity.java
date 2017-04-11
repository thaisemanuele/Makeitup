package com.natura.teste.thaissantos.makeitup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.ndk.CrashlyticsNdk;

import com.crashlytics.android.answers.Answers;
import io.fabric.sdk.android.Fabric;



public class MainActivity extends AppCompatActivity {

    private static int CAM_RESULT = 0;
    private static int IMAGE_RESULT = 1;
    DataBaseHelper db = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());
        Fabric.with(this, new Answers());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        String name =  extras.getString("name");

        db.populate(db.productFactory());

        TextView welcome = (TextView) findViewById(R.id.textView) ;
        welcome.setText("Bem Vindo, "+name);

        ImageView imView = (ImageView) findViewById(R.id.imageView);

        imView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                ImageView imView = (ImageView) findViewById(R.id.imageView);
                Bitmap bitmap = imView.getDrawable() == null ? null : ((BitmapDrawable)imView.getDrawable()).getBitmap();
                Rect imgBounds = imView.getDrawable() == null ? null : imView.getDrawable().getBounds();
                if(bitmap != null){
                    float ratioX = imView.getWidth()/ bitmap.getWidth();
                    float ratioY = imView.getHeight()/ bitmap.getHeight();
                    float[] pts = {
                            event.getX() , event.getY()
                    };
                    int pixel =0;
                    Double posX = ratioX == 0 ? Double.valueOf(pts[0]): Double.valueOf(pts[0]/ratioX);
                    Double posY = ratioY == 0 ? Double.valueOf(pts[1]): Double.valueOf(pts[1]/ratioY);
                    try{
                        int pixels[] = new int[50];
                        bitmap.getPixels(pixels, 0, 4, posX.intValue(), posY.intValue(), 3, 3);

                        pixel = CalculatorUtils.medPixel(pixels,9);

                        int redValue = Color.red(pixel);
                        int blueValue = Color.blue(pixel);
                        int greenValue = Color.green(pixel);

                        Button confirmColor = (Button) findViewById(R.id.button);
                        confirmColor.setBackgroundColor(Color.argb(120, redValue, greenValue, blueValue));
                        return false;
                    }
                    catch (Exception e){
                        Log.d("Main","Failed to get pixel values "+e.getMessage());
                    }

                    return false;
                }

                return false;
            }
        });
        Button confirmButton = (Button) findViewById(R.id.button);
        confirmButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(v.getBackground() instanceof  ColorDrawable){
                            ColorDrawable color = (ColorDrawable) v.getBackground();
                            Intent intent = new Intent(v.getContext(), DisplayCatalogActivity.class);

                            intent.putExtra("r", Color.red(color.getColor()));
                            intent.putExtra("g", Color.green(color.getColor()));
                            intent.putExtra("b", Color.blue(color.getColor()));
                            startActivity(intent);
                        }
                    }
                }
        );
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton cam = (FloatingActionButton) findViewById(R.id.cam);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,IMAGE_RESULT);
            }
        });

        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentCam,CAM_RESULT);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == IMAGE_RESULT && resultCode == RESULT_OK
                    && null != data) {
                Uri selectedImage = data.getData();
                //Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ImageView loadedImage = (ImageView) findViewById(R.id.imageView);
                loadedImage.setImageURI(null);
                loadedImage.setImageURI(selectedImage);
            }
            if (requestCode == CAM_RESULT && resultCode == RESULT_OK
                    && null != data) {
                //Uri selectedImage = data.getData();
                ImageView captedImage = (ImageView) findViewById(R.id.imageView);

                Uri selectedImage = PictureHelper.getPictureTaken(this);
                captedImage.setImageURI(null);
                captedImage.setImageURI(selectedImage);
            }
        }
        catch (Exception e) {
            Toast.makeText(this, "Error Uploading Image", Toast.LENGTH_LONG).show();
        }
    }

}
