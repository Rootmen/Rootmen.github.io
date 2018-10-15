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
        private int j=0,k=0,i=0;
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
                MkText = MkText + "\n       Pr"+j+"=R+S+Co=0+"+"Pr"+i+"+1="+toHex(Pr[j]);
                Pr[i]=Pr[j];
            }
            else if (MKi == 2) {
                MkText = MkText + "\nМК-2";
                MkText = MkText + "\n       Pr"+j+"=R+S+Co=0+Pr"+i+"Pr"+j+"+0="+toHex(Pr[j]+Pr[i]);
                Pr[j]=Pr[j]+Pr[i];
            }
            else if (MKi == 3) {
                MkText = MkText + "\nМК-3";
                MkText = MkText + "\n       PrA=Шине входа=" + toHex(B);
                PrA=B;
                MkText = MkText + "\n       PrB=(Прием данных с Шн.Д)="+toHex(PrD);
                if(PrD==null){  MkText = MkText + "\n PrD - Шина данных еще не опредлена"; return false; }
                PrB = PrD;
                MkText = MkText + "\n       PrС=(Хранение)=" + toHex(PrC);
                MkText = MkText + "\n       PrD=Шине Данных, выдает даные которые хранились раньше на Шн.д=" + toHex(B);
                PrD = B;
            }
            else if (MKi == 4) {
                MkText = MkText + "\nМК-4";
                MkText = MkText + "\n       PrA=(Хранение)=" + toHex(PrA);
                MkText = MkText + "\n       PrB=(Обнулился)=" + 0;
                PrB = new Integer(0);
                MkText = MkText + "\n       PrС=(Хранение)=" + toHex(PrC);
                MkText = MkText + "\n       PrD=Шине Данных=" + toHex(B);
                PrD = B;
            }
            else if (MKi == 5) {
                MkText = MkText + "\nМК-5";
                MkText = MkText + "\n       PrA=Шине Данных=" + toHex(B);
                PrA=B;
                MkText = MkText + "\n       PrB=(Прием данных с Шн.Д)="+toHex(PrD);
                if(PrD==null){  MkText = MkText + "\n PrD - Шина данных еще не опредлена"; return false; }
                MkText = MkText + "\n       PrС=(Хранение)=" + toHex(PrC);
                MkText = MkText + "\n       PrD=(Хранение, выдача на Шн.д)=" + toHex(PrD);
            }
            else if (MKi == 6) {
                MkText = MkText + "\nМК-6";
                MkText = MkText + "\n       PrA=(Хранение, выдача на Шн.д)=" + toHex(PrA);
                MkText = MkText + "\n       PrB=(Обнулился)=" + 0;
                PrB = new Integer(0);
                MkText = MkText + "\n       PrC=(Прием с АЛУ)=ИНВЕРСИЯ A=" + toHex(~PrA);
                PrC=~PrA;
                MkText = MkText + "\n       PrD=Шине входа=" + toHex(B);
                PrD=B;
            }
            else if (MKi == 7) {
                MkText = MkText + "\nМК-7";
                MkText = MkText + "\n       PrA=(Хранение)=" + toHex(PrA);
                MkText = MkText + "\n       PrB=(Прием данных с Шн.Д)="+toHex(PrD);;
                PrB = PrD;
                MkText = MkText + "\n       PrC=(Хранение)=" + toHex(PrC);
                MkText = MkText + "\n       PrD=(Хранение, выдача на Шн.д)=" + toHex(PrD);
            }
            else if (MKi == 8) {
                MkText = MkText + "\nМК-8";
                MkText = MkText + "\n       PrA=(Хранение)=" + toHex(PrA);
                MkText = MkText + "\n       PrB=(Хранение)=" +toHex(PrB);
                MkText = MkText + "\n       PrC=(PrC-PrB-1)=" + toHex(PrC-PrB-1);
                PrC=PrC-PrB-1;
                MkText = MkText + "\n       PrD=(Хранение, выдача на Шн.д)=" + toHex(PrD);
            }
            else if (MKi == 9) {
                MkText = MkText + "\nМК-9";
                MkText = MkText + "\n       PrA=(Хранение)=" + toHex(PrA);
                MkText = MkText + "\n       PrB=(Хранение)=" +toHex(PrB);
                MkText = MkText + "\n       PrC=(PrC+PrB+1)=" + toHex(PrC+PrB+1);
                PrC=PrC+PrB+1;
                MkText = MkText + "\n       PrD=(Хранение, выдача на Шн.д)=" + toHex(PrD);
            }
            else if (MKi == 10) {
                MkText = MkText + "\nМК-10";
                MkText = MkText + "\n       PrA=(Хранение)=" + toHex(PrA);
                MkText = MkText + "\n       PrB=(Шина данных)=" +toHex(PrC);
                PrB=PrC;
                MkText = MkText + "\n       PrC=(Хранение, выдача на Шн.д)=" + toHex(PrC);
                MkText = MkText + "\n       PrD=(Хранение)=" + toHex(PrD);
            }
            else if (MKi == 11) {
                MkText = MkText + "\nМК-11";
                MkText = MkText + "\n       PrA=(Хранение)=" + toHex(PrA);
                MkText = MkText + "\n       PrB=(Хранение)=" +toHex(PrC);
                MkText = MkText + "\n       PrC=(Хранение, выдача на Шн.д)=" + toHex(PrC);
                MkText = MkText + "\n       PrD=(Хранение)=" + toHex(PrD);
            }
            else if (MKi == 12) {
                MkText = MkText + "\nМК-12";
                MkText = MkText + "\n       PrA=(Хранение, выдача на Шн.д)=" + toHex(PrA);
                MkText = MkText + "\n       PrB=(Хранение)=" +toHex(PrC);
                MkText = MkText + "\n       PrC=(AЛУ)=PrA+PrB+1=" + toHex(PrA+PrB+1);
                PrC=PrA+PrB+1;
                MkText = MkText + "\n       PrD=(Хранение)=" + toHex(PrD);
            }
            else if (MKi == 13) {
                MkText = MkText + "\nМК-13";
                MkText = MkText + "\n       PrA=(Хранение, выдача на Шн.д)=" + toHex(PrA);
                MkText = MkText + "\n       PrB=(Сдвиг вправо(не увеерен что правильно))=" +toHex(PrB >> 1);

                MkText = MkText + "\n       PrC=(AЛУ)=PrA+PrB=" + toHex(PrA-PrB-1);
                PrC=PrA-PrB-1;
                MkText = MkText + "\n       PrD=(Хранение)=" + toHex(PrD);
                PrB = PrB >> 1;
            }
            else if (MKi == 14) {
                MkText = MkText + "\nМК-14";
                MkText = MkText + "\n       PrA=(Хранение)=" + toHex(PrA);
                MkText = MkText + "\n       PrB=(Сдвиг вправо(не увеерен что правильно))=" +toHex(PrB >> 1);

                MkText = MkText + "\n       PrC=(AЛУ)=PrD+PrB=" + toHex(PrD+PrB);
                PrC=PrD+PrB;
                MkText = MkText + "\n       PrD=(Хранение, выдача на Шн.д)=" + toHex(PrD);
                PrB = PrB >> 1;
            }
            else if (MKi == 15) {
                MkText = MkText + "\nМК-15 не поддерживаетсяfg(До сих пор)";
                return false;
            }
            return true;
        }
        String toHex(Integer Int){
            if(Int==null) return "null";
            return Integer.toString(Int,16);
        }
    }
