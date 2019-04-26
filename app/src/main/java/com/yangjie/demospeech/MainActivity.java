package com.yangjie.demospeech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageview = findViewById(R.id.imageview);

        MyBitmapUtils myBitmapUtils = new MyBitmapUtils();
        myBitmapUtils.display(imageview,"http://i.imgur.com/DvpvklR.png");
    }
}
