package com.example.franciscofranco.picturetesting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.File;

public class Main2Activity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView = (ImageView) findViewById(R.id.imageView);
        Intent intent = getIntent();
        String filePath = intent.getStringExtra(MainActivity.FILEPATH);
        setImage(filePath);
    }


    public void setImage(String filePath) {
        File imageFile = new File(filePath);
        if (imageFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.toString());
            imageView.setImageBitmap(Bitmap.createScaledBitmap(myBitmap, 800, 1000, false));
        }
    }
}
