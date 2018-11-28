package com.example.rootmen.bcchelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.pow;

public class KardanDM extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kardan_dm);
        Button KardanGo = findViewById(R.id.KardanGo);
        KardanGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KardanMain();
            }
        });

    }

    void KardanMain(){
        double aa,bb,cc,dd;
        TextView qText = null;
        try{
            qText=findViewById(R.id.KardanQ);
            bb = Integer.parseInt(((EditText)findViewById(R.id.KardanB)).getText().toString());
            cc = Integer.parseInt(((EditText)findViewById(R.id.KardanC)).getText().toString());
        }catch (Exception My){
            if(qText != null)
                qText.setText("Неверные данные");
            return;
        }

        Kardan ThisKardan = new Kardan(bb,cc);
        qText.setText(ThisKardan.getRezS().toString());

    }

    public class Kardan {
        private double a,b,c,d,p,q,s,f,x1,x2,x3,x2i,x3i;
        private double PI = 3.1415926535897932384626433832795;
        private StringBuilder TextQ = new StringBuilder();
        Kardan(double pp, double qq)  {;
            TextQ.append("Значения кофицентов\n P="+pp+" Q="+qq+"\n");
            p = pp;
            q = qq;
            s =(((q * q) * 27) + (p * p * p * 4)) / 27;
            double Qh = ((q*q)/4) + ((p*p*p)/27);
            double Ql = -q/2;
            TextQ.append("D = "+s+"\nt = v + u\n");
            TextQ.append("v^3 = -q/2 + root((q/2)^2+(p/3)^3) = "+Ql+" + root("+Qh+")\n");
            TextQ.append("u^3 = -q/2 - root((q/2)^2+(p/3)^3) = "+Ql+" - root("+Qh+")\n");
            TextQ.append("t = root3(v^3) + root3(u^3)\n");
        }


        public StringBuilder getRezS(){
            return TextQ;
        }

        public double[] getRez(){
            return new double[]{x1, x2, x3};
        }
    }

}
