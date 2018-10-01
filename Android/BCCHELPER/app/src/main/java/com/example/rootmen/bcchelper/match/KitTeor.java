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

public class KitTeor extends AppCompatActivity {
    DM.NODret NODretmy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kit_teor);
        final Button Start=findViewById(R.id.button);
        Start.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CutPasteId")
            @Override
            public void onClick(View v){
                int q1,q2,q3,q4,z,del;
                TextView qText=null;
                String Text="";
                int x=0;
                NODretmy=new DM.NODret();
                try{
                    qText=findViewById(R.id.qKitTekst);
                    q1=Integer.parseInt(((EditText)findViewById(R.id.q)).getText().toString());
                    q2=Integer.parseInt(((EditText)findViewById(R.id.q2)).getText().toString());
                    q3=Integer.parseInt(((EditText)findViewById(R.id.q3)).getText().toString());
                    q4=Integer.parseInt(((EditText)findViewById(R.id.q4)).getText().toString());
                    z=Integer.parseInt(((EditText)findViewById(R.id.Z)).getText().toString());
                }catch (Exception my){
                    assert qText != null;
                    qText.setText("Вы ввели неправильные данные");
                return;
                }
                int raz=Math.abs(q3-q1);
                if(q3-q1>0){
                NODretmy.gcd(q4,q2);
                Text="Приводим данное выражение к необходимому виду\n"+"x="+q1+"+"+q2+"*s="+q3+"+"+q4+"*t\n";
                Text=Text+q2+"*s-"+q4+"*t="+(raz)+"\n";
                Text=Text+ DM.NOD.gcd(q4,q2)+"\n";
                Text=Text+NODretmy.Qtext;
                int s=NODretmy.ag1*(raz);
                Text=Text+"s="+NODretmy.ag1+"*"+(raz)+"="+s+"\n";
                 x=q1+q2*s;
                Text=Text+"x="+q1+"+"+(q2)+"*"+s+"="+x+"\n";
                Text=Text+"x=? mod "+z+"\n";
                while (x>z) {
                    Text=Text+"?="+x+"-"+z+"="+(x-z)+"\n";
                    x=x-z;
                }}
                else {
                    NODretmy.gcd(q4,q2);
                    Text="Приводим данное выражение к необходимому виду\n"+"x="+q1+"+"+q2+"*s="+q3+"+"+q4+"*t\n";
                    Text=Text+q4+"*t-"+q2+"*s="+(raz)+"\n";
                    Text=Text+ DM.NOD.gcd(q4,q2)+"\n";
                    Text=Text+NODretmy.Qtext;
                    int s;
                    if(NODretmy.a1s==q4)
                    {
                         s=NODretmy.ag1*(raz);
                    Text=Text+"s="+NODretmy.ag1+"*"+(raz)+"="+s+"\n";
                    x=q3+q4*s;
                    }
                    else {
                        s=NODretmy.ag1*(raz);
                        Text=Text+"s="+NODretmy.ag1+"*"+(raz)+"="+s+"\n";
                        x=q3+q2*s;
                    }
                    if(x<0)
                        x=z-x;
                    Text=Text+"x="+q1+"+"+(q2)+"*"+s+"="+x+"\n";
                    Text=Text+"x=? mod "+z+"\n";
                    while (x>z) {
                        Text=Text+"?="+x+"-"+z+"="+(x-z)+"\n";
                        x=x-z;
                    }
                }
                Text=Text+"?="+x+"\n";
                qText.setText(Text);
                qText.setMovementMethod(new ScrollingMovementMethod());
            }});
        }

}
