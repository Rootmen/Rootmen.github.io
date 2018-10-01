package com.example.rootmen.bcchelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class StartActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_start);
        ImageView myImageView = (ImageView)findViewById(R.id.logoanim);
        intent = new Intent(this, Menu.class);
        Thread logoTimer = new Thread() {
            public void run() {
                try {
                    int logoTimer = 0;
                    while(logoTimer < 2000) {
                        sleep(100);
                        logoTimer = logoTimer +100;
                    };
                    startActivity(intent);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    finish();
                } }};
        logoTimer.start();
    }
}