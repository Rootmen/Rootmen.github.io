package com.example.rootmen.bcchelper.EVM;

import android.content.Intent;
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

import com.example.rootmen.bcchelper.Menu;
import com.example.rootmen.bcchelper.R;

public class EVMLB1 extends AppCompatActivity {
    Integer PrA,PrB,PrC,PrD;
    int A,B;
    String MkText=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evmlb1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button go = (Button) findViewById(R.id.evago);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               toMk();
            }
        });

    }

    void toMk(){
        TextView Qtext=null;
        String MK=null;
        try{
            Qtext = (TextView) findViewById(R.id.eva1qtext);
            Qtext.setMovementMethod(new ScrollingMovementMethod());
            MK=((TextView) findViewById(R.id.mknum)).getText().toString();
                if(MK.length()==0)
                    throw new Exception();

            PrA=null;PrB=null;PrC=null;PrD=null;
            A=Integer.parseInt((((EditText)findViewById(R.id.sha)).getText().toString()),16);
            B=Integer.parseInt((((EditText)findViewById(R.id.shb)).getText().toString()),16);
        }catch (Exception My){
            if(Qtext!=null)  Qtext.setText("Ошибка при чтении данных проверьте правильности и последовательность МК");
        }
        MkText=new String("");
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
        if (MKi == 1) {
            MkText = MkText + "\nМК-1";
            MkText = MkText + "\n       PrA=Шине входа=" + toHex(A);
                    PrA = A;
            MkText = MkText + "\n       PrB=(Обнулился)=" + 0;
                    PrB = new Integer(0);
            MkText = MkText + "\n       PrС=Хранение данных=" + toHex(PrC);
            MkText = MkText + "\n       PrD=Хранение данных=" + toHex(PrD);
        }
        else if (MKi == 2) {
            MkText = MkText + "\nМК-2";
            MkText = MkText + "\n       PrA=Хранение данных="+ toHex(PrA);
            MkText = MkText + "\n       PrB=(Обнулился)="+0;
                    PrB = new Integer(0);
            MkText = MkText + "\n       PrС=Хранение данных="+ toHex(PrC);
            MkText = MkText + "\n       PrD=Шине входа=" + toHex(A);
                    PrD = A;
        }
        else if (MKi == 3) {
            MkText = MkText + "\nМК-3";
            MkText = MkText + "\n       PrA=Хранение данных c выдачей на Шн.Д="+ toHex(PrA);
            MkText = MkText + "\n       PrB=(Хранение)="+0;
                    PrB = new Integer(0);
            MkText = MkText + "\n       PrС=Прием с АЛУ=A+B=Шн.Д+PrB=" + toHex((PrA+PrB));
                    PrC=PrA+PrB;
            MkText = MkText + "\n       PrD=Шине входа=" + toHex(B);
                    PrD = B;
        }
        else if (MKi == 4) {
            MkText = MkText + "\nМК-4";
            MkText = MkText + "\n       PrA=Шине входа=" + toHex(B);
                    PrA=B;
            MkText = MkText + "\n       PrB=(Прием данных с Шн.Д)="+toHex(PrD);
            if(PrD==null){  MkText = MkText + "\n PrD - Шина данных еще не опредлена"; return false; }
                     PrB = PrD;
            MkText = MkText + "\n       PrС=(Хранение)=" + toHex(PrC);
            MkText = MkText + "\n       PrD=Шине Данных, выдает даные которые хранились раньше на Шн.д=" + toHex(B);
                        PrD = B;
        }
        else if (MKi == 5) {
            MkText = MkText + "\nМК-5";
            MkText = MkText + "\n       PrA=(Хранение)=" + toHex(PrA);
            MkText = MkText + "\n       PrB=(Обнулился)=" + 0;
                        PrB = new Integer(0);
            MkText = MkText + "\n       PrС=(Хранение)=" + toHex(PrC);
            MkText = MkText + "\n       PrD=Шине Данных=" + toHex(B);
                        PrD = B;
        }
        else if (MKi == 6) {
            MkText = MkText + "\nМК-6";
            MkText = MkText + "\n       PrA=Шине Данных=" + toHex(B);
                        PrA=B;
            MkText = MkText + "\n       PrB=(Прием данных с Шн.Д)="+toHex(PrD);
            if(PrD==null){  MkText = MkText + "\n PrD - Шина данных еще не опредлена"; return false; }
            MkText = MkText + "\n       PrС=(Хранение)=" + toHex(PrC);
            MkText = MkText + "\n       PrD=(Хранение, выдача на Шн.д)=" + toHex(PrD);
        }
        else if (MKi == 7) {
            MkText = MkText + "\nМК-7";
            MkText = MkText + "\n       PrA=(Хранение, выдача на Шн.д)=" + toHex(PrA);
            MkText = MkText + "\n       PrB=(Обнулился)=" + 0;
                        PrB = new Integer(0);
            MkText = MkText + "\n       PrC=(Прием с АЛУ)=ИНВЕРСИЯ A=" + toHex(~PrA);
                        PrC=~PrA;
            MkText = MkText + "\n       PrD=Шине входа=" + toHex(B);
                        PrD=B;
        }
        else if (MKi == 8) {
            MkText = MkText + "\nМК-8";
            MkText = MkText + "\n       PrA=(Хранение)=" + toHex(PrA);
            MkText = MkText + "\n       PrB=(Прием данных с Шн.Д)="+toHex(PrD);;
                    PrB = PrD;
            MkText = MkText + "\n       PrC=(Хранение)=" + toHex(PrC);
            MkText = MkText + "\n       PrD=(Хранение, выдача на Шн.д)=" + toHex(PrD);
        }
        else if (MKi == 9) {
            MkText = MkText + "\nМК-9";
            MkText = MkText + "\n       PrA=(Хранение)=" + toHex(PrA);
            MkText = MkText + "\n       PrB=(Хранение)=" +toHex(PrB);
            MkText = MkText + "\n       PrC=(PrC-PrB-1)=" + toHex(PrC-PrB-1);
                    PrC=PrC-PrB-1;
            MkText = MkText + "\n       PrD=(Хранение, выдача на Шн.д)=" + toHex(PrD);
        }
        else if (MKi == 10) {
            MkText = MkText + "\nМК-10";
            MkText = MkText + "\n       PrA=(Хранение)=" + toHex(PrA);
            MkText = MkText + "\n       PrB=(Хранение)=" +toHex(PrB);
            MkText = MkText + "\n       PrC=(PrC+PrB+1)=" + toHex(PrC+PrB+1);
                    PrC=PrC+PrB+1;
            MkText = MkText + "\n       PrD=(Хранение, выдача на Шн.д)=" + toHex(PrD);
        }
        else if (MKi == 11) {
            MkText = MkText + "\nМК-11";
            MkText = MkText + "\n       PrA=(Хранение)=" + toHex(PrA);
            MkText = MkText + "\n       PrB=(Шина данных)=" +toHex(PrC);
                    PrB=PrC;
            MkText = MkText + "\n       PrC=(Хранение, выдача на Шн.д)=" + toHex(PrC);
            MkText = MkText + "\n       PrD=(Хранение)=" + toHex(PrD);
        }
        else if (MKi == 12) {
            MkText = MkText + "\nМК-12";
            MkText = MkText + "\n       PrA=(Хранение)=" + toHex(PrA);
            MkText = MkText + "\n       PrB=(Хранение)=" +toHex(PrC);
            MkText = MkText + "\n       PrC=(Хранение, выдача на Шн.д)=" + toHex(PrC);
            MkText = MkText + "\n       PrD=(Хранение)=" + toHex(PrD);
        }
        else if (MKi == 13) {
            MkText = MkText + "\nМК-13";
            MkText = MkText + "\n       PrA=(Хранение, выдача на Шн.д)=" + toHex(PrA);
            MkText = MkText + "\n       PrB=(Хранение)=" +toHex(PrC);
            MkText = MkText + "\n       PrC=(AЛУ)=PrA+PrB+1=" + toHex(PrA+PrB+1);
                    PrC=PrA+PrB+1;
            MkText = MkText + "\n       PrD=(Хранение)=" + toHex(PrD);
        }
        else if (MKi == 14) {
            MkText = MkText + "\nМК-14";
            MkText = MkText + "\n       PrA=(Хранение, выдача на Шн.д)=" + toHex(PrA);
            MkText = MkText + "\n       PrB=(Сдвиг вправо(не увеерен что правильно))=" +toHex(PrB >> 2);

            MkText = MkText + "\n       PrC=(AЛУ)=PrA+PrB=" + toHex(PrA-PrB-1);
                    PrC=PrA-PrB-1;
            MkText = MkText + "\n       PrD=(Хранение)=" + toHex(PrD);
            PrB = PrB >> 1;
        }
        else if (MKi == 15) {
            MkText = MkText + "\nМК-15";
            MkText = MkText + "\n       PrA=(Хранение)=" + toHex(PrA);
            MkText = MkText + "\n       PrB=(Сдвиг вправо(не увеерен что правильно))=" +toHex(PrB >> 2);

            MkText = MkText + "\n       PrC=(AЛУ)=PrD+PrB=" + toHex(PrD+PrB);
            PrC=PrD+PrB;
            MkText = MkText + "\n       PrD=(Хранение, выдача на Шн.д)=" + toHex(PrD);
            PrB = PrB >> 1;
        }
        else if (MKi == 16) {
            MkText = MkText + "\nМК-16 не поддерживаеться";
            return false;
        }
        else if (MKi == 17) {
            MkText = MkText + "\nМК-17 не поддерживаеться";
            MkText = MkText + "\n       PrA=(Хранение)=" + toHex(PrA);
            MkText = MkText + "\n       PrB=(Хранение)=" +toHex(PrB);

            MkText = MkText + "\n       PrC=(AЛУ)=Av(инверсная B)=" + toHex(PrD|(~PrB));
            PrC=PrD|(~PrB);
            MkText = MkText + "\n       PrD=(Хранение, выдача на Шн.д)=" + toHex(PrD);
        }
        else if (MKi == 18) {
            MkText = MkText + "\nМК-18 не поддерживаеться";
            MkText = MkText + "\n       PrA=(Хранение)=" + toHex(PrA);
            MkText = MkText + "\n       PrB=(Сброс)=" +toHex(0);
            PrB=0;
            MkText = MkText + "\n       PrC=(AЛУ)=PrD=" + toHex(PrD);
            PrC=PrD;
            MkText = MkText + "\n       PrD=(Хранение, выдача на Шн.д)=" + toHex(PrD);
        }
        else if (MKi == 19) {
            MkText = MkText + "\nМК-15";
            MkText = MkText + "\n       PrA=(Хранение)=" + toHex(PrA);
            MkText = MkText + "\n       PrB=(Сдвиг вправо(не увеерен что правильно))=" +toHex(PrB >> 2);

            MkText = MkText + "\n       PrC=(AЛУ)=PrD+PrB=" + toHex(PrD+PrB);
            PrC=PrD+PrB;
            MkText = MkText + "\n       PrD=(Хранение, выдача на Шн.д)=" + toHex(PrD);
            PrB = PrB << 1;
            String B2=Integer.toBinaryString(PrB);
            String B3="";
            for(int g=0;g<8&&g<B2.length();g++) B3=B3+B2.charAt(g);
            PrB=Integer.parseInt(B2,2);
        }
        return true;
    }
    String toHex(Integer Int){
        if(Int==null) return "null";
        return Integer.toString(Int,16);
    }
}
