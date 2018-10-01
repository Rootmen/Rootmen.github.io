package com.example.rootmen.bcchelper.avtomat.buli;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ScrollingView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rootmen.bcchelper.R;

import java.util.ArrayList;
import java.util.List;

public class Bule extends AppCompatActivity {
    int x=0,y=0;
    LinearLayout llt;
    LinearLayout My[];
    EditText MyText[][];
    TextView MyTextVivri[];
    LinearLayout.LayoutParams lEditParams;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bule);
        List<LinearLayout> LinearLayoutMass = new ArrayList<LinearLayout>();
        llt = (LinearLayout) findViewById(R.id.master);
        llt.setOverScrollMode(ScrollView.OVER_SCROLL_IF_CONTENT_SCROLLS);
        lEditParams = new LinearLayout.LayoutParams(
                60, 80);
        ScrollView Scrol= findViewById(R.id.Scrol);
        Scrol.setOverScrollMode(ScrollView.OVER_SCROLL_IF_CONTENT_SCROLLS);
        Button StartTTL =  findViewById(R.id.goblue);
        StartTTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                int editId = v.getId();
                x=(int)Math.pow(2,Integer.parseInt(((TextView)findViewById(R.id.iks)).getText().toString()));
                int zEt=Integer.parseInt(((TextView)findViewById(R.id.zet)).getText().toString());
                int xIk=Integer.parseInt(((TextView)findViewById(R.id.iks)).getText().toString());
                y=xIk+zEt;
                llt.removeAllViews();
                llt.setOrientation(LinearLayout.VERTICAL);
                My = new LinearLayout[x+1];
                MyText = new EditText[x][y];
                MyTextVivri = new TextView[y];
                My[0] =new LinearLayout(Bule.this);
                My[0].setOrientation(LinearLayout.HORIZONTAL);
                for(int g=0;g<xIk;g++){
                    MyTextVivri[g]=new TextView(Bule.this);
                    MyTextVivri[g].setText("x"+g);
                    MyTextVivri[g].setGravity(Gravity.CENTER_HORIZONTAL);
                    My[0].addView(MyTextVivri[g],lEditParams);
                }
                for(int g=0;g<zEt;g++){
                    MyTextVivri[g+xIk]=new TextView(Bule.this);
                    MyTextVivri[g+xIk].setText("z"+g);
                    MyTextVivri[g+xIk].setGravity(Gravity.CENTER_HORIZONTAL);
                    My[0].addView(MyTextVivri[g+xIk],lEditParams);
                }
                llt.addView(My[0]);
                for(int k = 1;k < x; k++) {
                    My[k] =new LinearLayout(Bule.this);
                    My[k].setOrientation(LinearLayout.HORIZONTAL);
                for(int g = 0;g < y; g++){
                    MyText[k][g]=new EditText(Bule.this);
                    MyText[k][g].setInputType(InputType.TYPE_CLASS_PHONE);
                    MyText[k][g].setId(k*10+g);
                    My[k].addView(MyText[k][g],lEditParams);
                }
                llt.addView(My[k]);

            }
        }});
    }

}
