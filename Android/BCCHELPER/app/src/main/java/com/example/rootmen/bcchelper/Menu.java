package com.example.rootmen.bcchelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rootmen.bcchelper.EVM.EVMLB1;
import com.example.rootmen.bcchelper.EVM.EVMLB2;
import com.example.rootmen.bcchelper.avtomat.TAVT;
import com.example.rootmen.bcchelper.match.DM;
import com.example.rootmen.bcchelper.menu.Sup;
import com.example.rootmen.bcchelper.shem.ttl;

public class Menu extends AppCompatActivity {

    private static final int MILLIS_PER_SECOND = 1000;
    private static final int SECONDS_TO_COUNTDOWN = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button StartTTL =  findViewById(R.id.startCHEM);
        StartTTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, ttl.class);
                startActivity(intent);
            }
        });
        Button startMatch = findViewById(R.id.startMatch);
        startMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, DM.class);
                startActivity(intent);
            }
        });
        Button startAVTOMAT = findViewById(R.id.startAVTOMAT);
        startAVTOMAT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, EVMLB1.class);
                startActivity(intent);
            }
        });
        Button startAVTOMAT2 = findViewById(R.id.startAVTOMAT2);
        startAVTOMAT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, EVMLB2.class);
                startActivity(intent);
            }
        });
        Button startAVTOMAT3 = findViewById(R.id.startAVTOMAT3);
        startAVTOMAT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, KardanDM.class);
                startActivity(intent);
            }
        });
        ImageView imag = findViewById(R.id.logo);
        imag.setClickable(true);
        imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Sup.class);
                startActivity(intent);

            }
        });
    }}


