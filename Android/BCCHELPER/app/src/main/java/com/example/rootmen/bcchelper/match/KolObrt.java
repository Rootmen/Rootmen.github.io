package com.example.rootmen.bcchelper.match;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rootmen.bcchelper.R;

public class KolObrt extends AppCompatActivity {
    DM.NODret NODretmy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kol_obrt);
        final Button Start = findViewById(R.id.button3);
        Start.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CutPasteId")
            @Override
            public void onClick(View v) {
                int Z,elem;
                TextView qText=null;
                String Text="";
                NODretmy=new DM.NODret();
                try{
                    qText=findViewById(R.id.textView17);
                    Z=Integer.parseInt(((EditText)findViewById(R.id.Z)).getText().toString());
                    elem=Integer.parseInt(((EditText)findViewById(R.id.elem)).getText().toString());
                }catch (Exception my){
                    assert qText != null;
                    qText.setText("Вы ввели неправильные данные");
                    return;
                }
                qText.setText(GetObElem(Z,elem));
                qText.setMovementMethod(new ScrollingMovementMethod());
            }
        });
    }
    String GetObElem(int Z,int elem)
    {
        TextView qText=null;
        String Text="";
        NODretmy=new DM.NODret();
        Text=Text+DM.NOD.gcd(Z,elem);
        NODretmy.gcd(Z,elem);
        Text=Text+NODretmy.Qtext;
        int x=0;
        if(NODretmy.a1s==elem) {
            x=NODretmy.ag1;
            if(x<0) {
                x=Z-Math.abs(x);
            }
            Text=Text+NODretmy.ag1+"="+"? mod "+Z+"\n";
            Text=Text+"?="+x+"\n";
        }
        else{
            x=NODretmy.bg1;
            if(x<0) {
                x=Z-Math.abs(x);
            }
            Text=Text+NODretmy.bg1+"="+"? mod "+Z+"\n";
            Text=Text+"?="+x+"\n";
        }
            return Text;
    }
}

