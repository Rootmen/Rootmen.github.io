package com.example.rootmen.bcchelper.EVM;

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

public class EVMLB2 extends AppCompatActivity {
        private int Pr[] = new int[16];
        private String MkText="";
        private int j=0,k=0,i=0,PQ=0,D1=0,D2=0;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_evmlb2);
            Button go = (Button) findViewById(R.id.EVMLB2_Go);
            go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toMk();
                }
            });

        };

        void toMk(){
            TextView Qtext=null;
            String MK=null;
            try{
                Qtext = (TextView) findViewById(R.id.EVMLB2_text);
                Qtext.setMovementMethod(new ScrollingMovementMethod());
                MK=((TextView) findViewById(R.id.EVMLB2_MK)).getText().toString();
                if(MK.length()==0) throw new Exception();
                for(int g=0;g<16;g++) Pr[g]=g;
                j=Integer.parseInt((((EditText)findViewById(R.id.EVMLB2_J)).getText().toString()));
                k=Integer.parseInt((((EditText)findViewById(R.id.EVMLB2_k)).getText().toString()));
                i=Integer.parseInt((((EditText)findViewById(R.id.EVMLB2_I)).getText().toString()));
                PQ=Integer.parseInt((((EditText)findViewById(R.id.EVMLB2_PQ)).getText().toString()));
                D1=Integer.parseInt((((EditText)findViewById(R.id.EVMLB2_D1)).getText().toString()));
                D2=Integer.parseInt((((EditText)findViewById(R.id.EVMLB2_D2)).getText().toString()));


            }catch (Exception My){
                if(Qtext!=null)  Qtext.setText("Ошибка при чтении данных проверьте правильности и последовательность МК и данных");
            }
            MkText= "";
            String My[]=MK.split(" ");
            for(int g=0;g<My.length;g++) {
                if(!NextMK(My[g])){
                    MkText=MkText+"\nНа этом этапе что-то пошло не так проверьте введенные данные!!";
                    break;
                }
                for(int p=0;p<16;p++){
                    MkText = MkText + "\nPr"+p+"="+Integer.toBinaryString(Pr[p]);
                    if(Pr[p]<=15) continue;
                    String Pri=Integer.toBinaryString(Pr[p]);
                    String Pri1="";
                    for(int f=0;f<3;f++){
                        Pri1=Pri.charAt(f)+Pri1;
                    }
                    Pr[p]=Integer.parseInt(Pri1,2);
                }


            }
            Qtext.setText(MkText);
        }

        boolean NextMK(String MK) {
            int MKi;
            try {
                MKi = Integer.parseInt(MK);
            } catch (Exception My) {
                MkText = MkText + "\nЯ не смог определить номер MK";
                return false;
            }
            if (MKi == 0) {
                MkText = MkText + "\nМК-0";
                MkText = MkText + "\n       Pr"+i+"=R+S+Co=0+Pr"+i+"+1="+toHex(Pr[i]+1);
                Pr[i]=Pr[i]+1;
            }
            else if (MKi == 1) {
                MkText = MkText + "\nМК-1";
                MkText = MkText + "\n       Pr"+j+"=R+S+Co=0+"+"Pr"+j+"="+toHex(Pr[j]);
                Pr[j]=Pr[j];
            }
            else if (MKi == 2) {
                MkText = MkText + "\nМК-2";
                MkText = MkText + "\n       Pr"+j+"=R+S+Co=0+Pr"+i+"Pr"+j+"+0="+toHex(Pr[j]+Pr[i]);
                Pr[j]=Pr[j]+Pr[i];
            }
            else if (MKi == 3) {
                MkText = MkText + "\nМК-3";
                MkText = MkText + "\n       Pr"+i+"=Pr"+i+"<<PQ<<0";
                String Pri=Integer.toBinaryString(Pr[i]);
                String PQs=Integer.toBinaryString(PQ);
                String Pri1="",Pri2="";
                if(PQs.length()>3) Pri=Pri+PQs.charAt(3);
                else Pri=Pri+"0";
                PQs=PQs+"0";
                for(int g=0;g<4&&g<Pri.length();g++) Pri1=Pri.charAt(g)+Pri1;
                for(int g=0;g<4&&g<PQs.length();g++) Pri2=PQs.charAt(g)+Pri2;

                Pr[i]=Integer.parseInt(Pri1,2);
                PQ=Integer.parseInt(Pri2,2);
                MkText = MkText + "\n       Pr"+j+"="+toHex(Pr[j]);

                MkText = MkText + "\n       PQ="+toHex(PQ);
            }
            else if (MKi == 4) {
                MkText = MkText + "\nМК-4";
                MkText = MkText + "\n       Pr"+k+"=R+S+Co=1>>PQ";
                String PQs=Integer.toBinaryString(PQ);
                String Pri1="";
                for(int g=0;g<3;g++){
                    Pri1=PQs.charAt(g)+Pri1;
                }
                Pri1="1"+Pri1;
                PQ=Integer.parseInt(Pri1,2);
                Pr[k]=Integer.parseInt(Pri1,2);
                MkText = MkText + "\n       Pr"+k+"="+toHex(Pr[k]);

                MkText = MkText + "\n       PQ="+toHex(PQ);
            }
            else if (MKi == 5) {
                MkText = MkText + "\nМК-5";
                MkText = MkText + "\n       Pr"+i+"=Rr"+i+"+"+D1+"="+toHex(Pr[i]+D1);
                Pr[i]=Pr[i]+D1;
            }
            else if (MKi == 6) {
                MkText = MkText + "\nМК-6";
                MkText = MkText + "\n       Pr"+j+"=Pr"+j+"-"+D1+"-1+1="+toHex(Pr[i]-D1);
                Pr[i]=Pr[i]-D1;
            }
            else if (MKi == 7) {
                MkText = MkText + "\nМК-7";
                MkText = MkText + "\n       Pr"+j+"=Pr"+j+"+"+"Pr"+i+"="+toHex(Pr[i]+Pr[j]);
                Pr[j]=Pr[i]+Pr[j];
            }
            else if (MKi == 8) {
                MkText = MkText + "\nМК-8";
                MkText = MkText + "\n       Pr"+i+"=Pr"+i+"<<1";
                String PRi=Integer.toBinaryString(PQ);
                String Pri1="";
                for(int g=0;g<3;g++){
                    Pri1=PRi.charAt(g)+Pri1;
                }
                Pri1=Pri1+"1";
                Pr[i]=Integer.parseInt(Pri1,2);
                MkText = MkText + "\n       Pr"+i+"="+toHex(Pr[i]);
            }
            else if (MKi == 9) {
                MkText = MkText + "\nМК-9";
                MkText = MkText + "\n       Pr"+i+"=0>>Pr"+i+">>PQ";
                String Pri=Integer.toBinaryString(Pr[i]);
                String PQs=Integer.toBinaryString(PQ);
                PQs=Pri.charAt(0)+PQs;
                String Pri1="",Pri2="";
                for(int g=0;g<4;g++) Pri2=PQs.charAt(g)+Pri2;
                for(int g=1;g<4;g++) Pri1=Pri.charAt(g)+Pri1;
                Pri1="0"+Pri1;
                Pr[i]=Integer.parseInt(Pri1,2);
                PQ=Integer.parseInt(Pri2,2);
            }
            else if (MKi == 10) {
                MkText = MkText + "\nМК-10";
                MkText = MkText + "\n       Pr"+i+"=|(Pr"+i+"^0)="+toHex(~(Pr[i]^0));
                Pr[i]=~(Pr[i]^0);
            }
            else if (MKi == 11) {
                MkText = MkText + "\nМК-11";
                MkText = MkText + "\n       Pr7=Pr"+i+"|Pr7)="+toHex(Pr[i]|Pr[7]);
                Pr[7]=Pr[i]|Pr[7];
            }
            else if (MKi == 12) {
                MkText = MkText + "\nМК-12";
                MkText = MkText + "\n       Pr8=|(Pr"+i+"^Pr8)="+toHex(~(Pr[i]^Pr[8]));
                Pr[8]=~(Pr[i]^Pr[8]);
            }
            else if (MKi == 13) {
                MkText = MkText + "\nМК-13";
                MkText = MkText + "\n       Pr8=Pr8|Pr7="+toHex(Pr[8]|Pr[7]);
                Pr[8]=Pr[7]|Pr[8];
            }
            else if (MKi == 14) {
                MkText = MkText + "\nМК-14";
                MkText = MkText + "\n       Pr"+j+"=Pr"+j+"&Pr[8])="+toHex(Pr[j]&Pr[8]);
                Pr[j]=Pr[j]&Pr[8];
            }
            else if (MKi == 15) {
                MkText = MkText + "\nМК-15";
                MkText = MkText + "\n       Pr"+j+"=|(Pr"+j+"^0)="+toHex(~(Pr[j]^0));
                Pr[j]=~(Pr[j]^0);
            }
            return true;
        }
        String toHex(Integer Int){
            if(Int==null) return "null";
            return Integer.toString(Int,16);
        }
    }
