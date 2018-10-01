package com.example.rootmen.bcchelper.avtomat;

import android.content.Intent;
import android.graphics.BlurMaskFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rootmen.bcchelper.avtomat.buli.Bule;
import com.example.rootmen.bcchelper.R;
import com.example.rootmen.bcchelper.shem.ttl;

public class TAVT extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tavt);
        Button StartTTL =  findViewById(R.id.bluib);
        StartTTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TAVT.this, Bule.class);
                startActivity(intent);
            }
        });
    }
}
