package com.example.rootmen.bcchelper.EVM;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.rootmen.bcchelper.R;
import static java.lang.Math.abs;

public class EVMLB2 extends AppCompatActivity {
        private int Pr[] = new int[16];                     //Массив регистров
        private String MkText="";                           //Строка с решением
        private int j=15,k=15,i=15,PQ=15,D1=11,D2=5;        //Значения из задания
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
            for (String aMy : My) {
                if (!NextMK(aMy)) {
                    MkText = MkText + "\nНа этом этапе что-то пошло не так проверьте введенные данные!!";
                    break;
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
                Pr[i]=Pr[i]+1;
                Correct_Registry();
                MkText = MkText + "\n       Pr"+i+"=R+S+Co=0+Pr"+i+"+0+1="+toHex(Pr[i]);
            }
            else if (MKi == 1) {
                MkText = MkText + "\nМК-1";
                Pr[j]=Pr[j];
                Correct_Registry();
                MkText = MkText + "\n       Pr"+j+"=R+S+Co=0+"+"Pr"+j+"+0+0="+toHex(Pr[j]);
            }
            else if (MKi == 2) {
                MkText = MkText + "\nМК-2";
                Pr[j]=Pr[j]+Pr[i];
                Correct_Registry();
                MkText = MkText + "\n       Pr"+j+"=R+S+Co=Pr"+i+"+Pr"+j+"+0="+toHex(Pr[j]);
            }
            else if (MKi == 3) {
                MkText = MkText + "\nМК-3";
                MkText = MkText + "\n       Pr"+i+"=Pr"+i+"<<PQ<<0";
                String Pri=Integer.toBinaryString(Pr[i]);
                String PQs=Integer.toBinaryString(PQ);
                String Pri1="",Pri2="";
                if(PQs.length()==4) Pri=Pri+PQs.charAt(0);
                else Pri=Pri+"0";
                PQs=PQs+"0";
                for(int g=Pri.length()-1; g>-1 && g>Pri.length()-5; g--) Pri1=Pri.charAt(g)+Pri1;
                for(int g=PQs.length()-1; g>-1 && g>PQs.length()-5; g--) Pri2=PQs.charAt(g)+Pri2;
                Pr[i]=Integer.parseInt(Pri1,2);
                PQ=Integer.parseInt(Pri2,2);
                Correct_Registry();
                MkText = MkText + "\n       Pr"+i+"="+toHex(Pr[i]);
                MkText = MkText + "\n       PQ="+toHex(PQ);
            }
            else if (MKi == 4) {
                MkText = MkText + "\nМК-4";
                MkText = MkText + "\n       Pr"+k+"=R+S+Co=0+PQ+0";

                String PQs=Integer.toBinaryString(PQ);
                String Pri1="";
                while(PQs.length()<4) PQs = "0" + PQs;
                PQs="1"+PQs;
                for(int g = 0;g<4;g++)Pri1=Pri1+PQs.charAt(g);
                PQ=Integer.parseInt(Pri1,2);
                Pr[k]=Integer.parseInt(Pri1,2);
                Correct_Registry();
                MkText = MkText + "\n       Pr"+k+"=1>>Pr"+k+"="+toHex(Pr[k]);
                MkText = MkText + "\n       PQ=1>>PQ="+toHex(PQ);
            }
            else if (MKi == 5) {
                MkText = MkText + "\nМК-5";
                Pr[i]=Pr[i]+D1;
                Correct_Registry();
                MkText = MkText + "\n       Pr"+i+"=Rr"+i+"+"+D1+"="+toHex(Pr[i]);
            }
            else if (MKi == 6) {
                MkText = MkText + "\nМК-6";
                Pr[j]=Pr[j]-D2;
                if(Pr[j]<0) Pr[j] = 16 + Pr[j];
                Correct_Registry();
                MkText = MkText +"\n       Pr"+j+"=Pr"+j+"-"+D2+"-1+1="+toHex(Pr[j]);
            }
            else if (MKi == 7) {
                MkText = MkText + "\nМК-7";
                Pr[j]=Pr[i]+Pr[j];
                Correct_Registry();
                MkText = MkText + "\n       Pr"+j+"=Pr"+j+"+"+"Pr"+i+"="+toHex(Pr[j]);
            }
            else if (MKi == 8) {
                MkText = MkText + "\nМК-8";
                MkText = MkText + "\n       Pr"+i+"=Pr"+i+"<<1";
                String PRi=Integer.toBinaryString(Pr[i]);
                String Pri1="";
                for(int g=PRi.length();g<4;g++){
                    PRi="0"+PRi;
                }
                PRi=PRi+"1";
                for(int g = PRi.length()-1;g>-1&&g>PRi.length()-5;g--)
                    Pri1 = PRi.charAt(g) + Pri1;
                Pr[i]=Integer.parseInt(Pri1,2);
                Correct_Registry();
                MkText = MkText + "\n       Pr"+i+"="+toHex(Pr[i]);
            }
            else if (MKi == 9) {
                MkText = MkText + "\nМК-9";
                MkText = MkText + "\n       Pr"+i+"=0>>Pr"+i+">>PQ";
                String Pri=Integer.toBinaryString(Pr[i]);
                String PQs=Integer.toBinaryString(PQ);
                while(Pri.length()<4) Pri="0"+Pri;
                while(PQs.length()<4) PQs="0"+PQs;
                String Prl = "0"+Pri+PQs;
                String Pri1 = "", Pri2 = "";
                for(int g=0;g<4;g++){
                    Pri1=Pri1+Prl.charAt(g);
                    Pri2=Pri2+Prl.charAt(g+4);
                }
                Pr[i]=Integer.parseInt(Pri1,2);
                PQ=Integer.parseInt(Pri2,2);
                Correct_Registry();
                MkText = MkText + "\n       Pr"+i+"="+toHex(Pr[i]);
                MkText = MkText + "\n       PQ="+toHex(PQ);
            }
            else if (MKi == 10) {
                MkText = MkText + "\nМК-10";
                Pr[i]=~(Pr[i]);
                MkText = MkText + "\n       Pr"+i+"=|(Pr"+i+"^0)="+toHex(Pr[i]);
            }
            else if (MKi == 11) {
                MkText = MkText + "\nМК-11";
                Pr[8]=Pr[i]|Pr[8];
                MkText = MkText + "\n       Pr7=Pr"+i+"|Pr8)="+toHex(Pr[8]);
            }
            else if (MKi == 12) {
                MkText = MkText + "\nМК-12";
                Pr[9]=~(Pr[j]^Pr[9]);
                MkText = MkText + "\n       Pr9=|(Pr"+i+"^Pr9)="+toHex(Pr[9]);
            }
            else if (MKi == 13) {
                MkText = MkText + "\nМК-13";
                Pr[9]=Pr[8]|Pr[9];
                MkText = MkText + "\n       Pr9=Pr9|Pr8="+toHex( Pr[9]);
            }
            else if (MKi == 14) {
                MkText = MkText + "\nМК-14";
                Pr[j]=Pr[j]&Pr[9];
                MkText = MkText + "\n       Pr"+j+"=Pr"+j+"&Pr[9])="+toHex(Pr[j]);
            }
            else if (MKi == 15) {
                MkText = MkText + "\nМК-15";
                Pr[j]=~(Pr[j]);
                MkText = MkText + "\n       Pr"+j+"=|(Pr"+j+"^0)="+toHex(Pr[j]);
            }
            return true;
        }


        String toHex(Integer Int){
            if(Int==null) return "null";
            return Integer.toString(Int,16);
        }


        void Correct_Registry(){
            for(int p=0;p<16;p++){
                if(Pr[p]<0) Pr[p]=abs(Pr[p]);
                if(Pr[p]<=15) continue;
                String Pri=Integer.toBinaryString(Pr[p]);
                String Pri1="";
                for(int f=Pri.length()-1;f!=-1&&f>Pri.length()-5;f--){
                    Pri1=Pri.charAt(f)+Pri1;
                }
                Pr[p]=Integer.parseInt(Pri1,2);
            }
            if(PQ>15){
                String Pri=Integer.toBinaryString(PQ);
                String Pri1="";
                for(int f=Pri.length()-1;f!=-1&&f>Pri.length()-5;f--){
                    Pri1=Pri.charAt(f)+Pri1;
                }
                PQ=Integer.parseInt(Pri1,2);
            }
        }
    }
