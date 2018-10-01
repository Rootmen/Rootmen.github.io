package com.example.rootmen.bcchelper.shem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rootmen.bcchelper.R;

public class ttl extends AppCompatActivity {

    private static final int MILLIS_PER_SECOND = 1000;
    private static final int SECONDS_TO_COUNTDOWN = 30;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttl);
        Button StartTTL1 = findViewById(R.id.StartTTL1);
        StartTTL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ttl.this, ttl1.class);
                startActivity(intent);
            }
        });
        Button StartTTL2 = findViewById(R.id.StartTTL2);
        StartTTL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ttl.this, ttl2.class);
                startActivity(intent);
            }
        });
    }

    public static class ttl1 extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ttl1);
            Button GoTTL = findViewById(R.id.GoTTL1);
            GoTTL.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("DefaultLocale")
                @Override
                public void onClick(View v) {
                    TextView R1T = findViewById(R.id.R1N);
                    TextView RkT = findViewById(R.id.RkN);
                    TextView U0T = findViewById(R.id.U0N);
                    TextView U1T = findViewById(R.id.U1N);
                    TextView Bt2T = findViewById(R.id.BT2N);
                    TextView ttl1QT = findViewById(R.id.ttl1Q);

                    double Uben = 0.8, Ubkt = 0.6, Uken = 0.2;//Потери при переходах
                    double En = 5;//Напряжение питания
                    double R1, Rk;//Сопротивление R1
                    double U1, U0;//Уровни логического 0 и 1
                    double bt2;//Коф бета транзистора Т2


                    try {
                        R1 = Double.parseDouble(R1T.getText().toString());
                        Rk = Double.parseDouble(RkT.getText().toString());
                        U1 = Double.parseDouble(U1T.getText().toString());
                        U0 = Double.parseDouble(U0T.getText().toString());
                        bt2 = Double.parseDouble(Bt2T.getText().toString());
                    } catch (Exception my) {
                        ttl1QT.setText("Неаправильные данные");
                        return;
                    }
                    String TextQ;
                    TextQ = "1)Может ли схема работать на аналогичные схемы? Если да, то на сколько аналогичных схем?\n";
                    double Ibt2;//Ток через транзистор  T2
                    Ibt2 = (En - Ubkt - Uben) / R1;
                    TextQ = TextQ + String.format("Ibt2=(En-Ubkt-Uben)/R1=(%.2f-%.2f-%.2f)/%.2f=%.2f\n", En, Ubkt, Uben, R1, Ibt2);


                    double Irk;//Ток через резистор Rk
                    Irk = (En - Uken) / Rk;
                    TextQ = TextQ + String.format("Irk =(En-Uken)/Rk=(%.2f-%.2f)/%.2f=%.2f\n", En, Uken, Rk, Irk);


                    double Iovx;//Ток входной при нуле
                    Iovx = (En - Uben - U0) / R1;
                    TextQ = TextQ + String.format("Iovx =(En-Uben-U0)/R1=(%.2f-%.2f-%.2f)/%.2f=%.2f\n", En, Uben, U0, R1, Iovx);


                    //Выражение n из формулы
                    double Ndoub;
                    Ndoub = (Ibt2 * bt2 - Irk) / Iovx;
                    TextQ = TextQ + String.format("Ndoub=(Ibt2*bt2-Irk)/Iovx=(%.2f*%.2f-%.2f)/%.2f=%.2f\n", Ibt2, bt2, Irk, Iovx, Ndoub);
                    int n = (int) Ndoub;
                    TextQ = TextQ + "n=" + n + "\n";
                    TextQ = TextQ + "Схема может работать на " + n + " таких-же схем\n";


                    TextQ = TextQ + "2)При каком минимальном Rк схема сможет работать лишь на одну аналогичную схему?\n";
                    double RkMin = (En - Uken) / (bt2 * Ibt2 - 1 * Iovx);
                    TextQ = TextQ + String.format("Rk1Min=(En-Uken)/(bt2*Ibt2-1*Iovx)=(%.2f-%.2f)/(%.2f*%.2f-1*%.2f)=%.2f\n", En, Uken, bt2, Ibt2, Iovx, RkMin);
                    TextQ = TextQ + String.format("Минимальное значение Rk для работы на одну схему %.2f\n", RkMin);


                    TextQ = TextQ + ("3) При каком максимальном R1 схема сможет работать лишь на одну аналогичную схему?\n");
                    double R1Max = (bt2 * (En - Ubkt - Uben)) / (Iovx + Irk);
                    TextQ = TextQ + String.format("R1Max=(bt2*(En-Ubkt-Uben))/(Iovx+Irk)=(%.2f*(%.2f-%.2f-%.2f))/(%.2f+%.2f)=%.2f\n", bt2, En, Ubkt, Uben, Iovx, Irk, R1Max);


                    TextQ = TextQ + ("4)При каком минимальном bт2 схема сможет работать лишь на три аналогичные схемы?\n");
                    double bt2Min = (Irk + 3 * Iovx) / (Ibt2);
                    TextQ = TextQ + String.format("bt2Min=(Irk+3*Iovx)/(Ibt2)=(%.2f+3*%.2f)/(%.2f)=%.2f\n", Irk, Iovx, Ibt2, bt2Min);
                    ttl1QT.setText(TextQ);


                }
            });

        }
    }

    public static class ttl2 extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ttl2);
            Button GoTTL = findViewById(R.id.GoTTl2);

            GoTTL.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("DefaultLocale")
                @Override
                public void onClick(View v) {
                    TextView R1T = findViewById(R.id.R1T2);
                    TextView R2T = findViewById(R.id.R2T2);
                    TextView R3T = findViewById(R.id.R3T2);
                    TextView R4T = findViewById(R.id.R4T2);
                    TextView U0T = findViewById(R.id.U0T2);
                    TextView U1T = findViewById(R.id.U1T2);
                    TextView Bt2T = findViewById(R.id.BT2T2);
                    TextView Bt3T = findViewById(R.id.BT3T2);
                    TextView Bt4T = findViewById(R.id.BT4T2);
                    TextView ttl2QT = findViewById(R.id.ttl2Q);
                    ttl2QT.setMovementMethod(new ScrollingMovementMethod());
                    double Uben = 0.8, Uken = 0.5;//Потери при переходах
                    double En = 5;//Напряжение питания
                    double R1 = 30, R2 = 15, R3 = 10, R4 = 4;//Сопротивление R1
                    double U1 = 3.5, U0 = 0.5;//Уровни логического 0 и 1
                    double bt2 = 20, bt3 = 20, bt4 = 15;//Коф бета транзистора Т2
                    String TextQ = "";
                    try {
                        try {
                            R1 = Double.parseDouble(R1T.getText().toString());
                            R2 = Double.parseDouble(R2T.getText().toString());
                            R3 = Double.parseDouble(R3T.getText().toString());
                            R4 = Double.parseDouble(R4T.getText().toString());
                            U1 = Double.parseDouble(U1T.getText().toString());
                            U0 = Double.parseDouble(U0T.getText().toString());
                            bt2 = Double.parseDouble(Bt2T.getText().toString());
                            bt3 = Double.parseDouble(Bt3T.getText().toString());
                            bt4 = Double.parseDouble(Bt4T.getText().toString());
                        } catch (Exception my) {
                            TextQ="Неаправильные данные, будут использоваться данные по умолчанию\n";
                        }

                        TextQ = TextQ + ("1)При каком bт3 схема сможет работать не менее  чем на 20 аналогичных схем?\n");
                        double Iovx;//Ток входной при нуле
                        Iovx = (En - Uben - U0) / R1;
                        TextQ = TextQ + String.format("Iovx=(En-Uben-U0)/R1=(%.2f-%.2f-%.2f)/%.2f=%.2f\n", En, Uben, U0, R1, Iovx);

                        double Ir1;//Ток через r1
                        Ir1 = (En - 2 * Uben) / R1;
                        TextQ = TextQ + String.format("Ir1=(En-2*Uben)/R1=(%.2f-2*%.2f)/%.2f=%.2f\n", En, Uben, R1, Ir1);

                        double Ir2;//Ток через r2
                        Ir2 = Uben / R2;
                        TextQ = TextQ + String.format("Ir2=Uben/R2=%.2f/%.2f=%.2f\n", Uben, R2, Ir2);

                        double Ir3;//Ток через r3
                        Ir3 = (En - 1) / R3;
                        TextQ = TextQ + String.format("Ir3=(En-Uket2-Uket3)/R3=(%.2f-%.2f-%.2f)/%.2f=%.2f\n", En, 0.5, 0.5, R3, Ir3);

                        double Ibt3;//Ток через t3
                        Ibt3 = Ir1 + Ir3 - Ir2;
                        TextQ = TextQ + String.format("Ibt3=Ir1+Ir3-Ir2=%.2f+%.2f-%.2f=%.2f\n", Ir1, Ir3, Ir2, Ibt3);

                        double B;//Ток через резистор Rk
                        B = (20 * Iovx) / Ibt3;
                        TextQ = TextQ + String.format("B=(20*Iovx)/Ibt3=(20*%.2f)/%.2f=%.2f\n", Iovx, Ibt3, B);
                        TextQ = TextQ + String.format("B=%.2f, при работе на 20 аналогичных схем\n\n", B);


                        TextQ = TextQ + "2)При каком минимальном R2 величина Nмакс будет не менее 15?\n";
                        double R2n15;//Ток через резистор
                        R2n15 = (Uben * bt3) / (bt3 * (Ir1 + Ir3) - 15 * Iovx);
                        TextQ = TextQ + String.format("R2n15=(Uben*bt3)/(bt3*(Ir1+Ir3)-15*Iovx)=(%.2f*%.2f)/(%.2f*(%.2f+%.2f)-15*%.2f)=%.2f\n", Uben, bt3, bt3, Ir1, Ir3, Iovx, R2n15);
                        TextQ = TextQ + String.format("R2=%.2f, при работе на >15 аналогичных схем\n\n", R2n15);

                        TextQ = TextQ + "2)При каком R1 можно получить самое большое значение N макс?";
                        double R1max;//Ток через резистор
                        R1max = ((En - Uben - Uben) * bt2) / Ir3;
                        TextQ = TextQ + String.format("R1max=((En-Uben-Uben)*bt2)/Ir3=((%.2f-%.2f-%.2f)*%.2f)/%.2f=%.2f\n", En, Uben, Uben, bt2, Ir3, Iovx, R1max);
                        TextQ = TextQ + String.format("R1max=%.2f, при работе на max аналогичных схем\n\n", R1max);

                        TextQ = TextQ + "3)Определить U1вых, если к выходу подключен резистор R=2K, соединенный с землей. В каком режиме работает транзистор T4?\n";
                        double Ibt4;
                        Ibt4 = (En - Uben - 0.8) / R4;
                        TextQ = TextQ + String.format("Ibt4=(En-Uben-Ud)/R4=(%.2f-%.2f-0.80)/%.2f=%.2f\n", En, Uben, R4, Ibt4);

                        double Ikt4;
                        Ikt4 = (En - 0.5 - 0.5) / (R4 + 2);
                        TextQ = TextQ + String.format("Ikt4=(En-Uken-Ud)/(R4+Rn)=(En-0.50-0.50)/(R4+2)=%.2f\n", En, R4, Ikt4);
                        if (Ibt4 > (Ikt4 / bt4))
                            TextQ = TextQ + "Ibt4>(Ikt4/bt4) - режми насыщения\n";
                        else
                            TextQ = TextQ + "Ibt4<(Ikt4/bt4) - режми НЕ насыщения\n";

                        double U1vx;
                        U1vx = En - 1 - Ikt4 * 2;
                        TextQ = TextQ + String.format("U1vx=En-Uken-Ud-Ikt4*2=U1vx=%.2f-0.50-0.50-%.2f*2=%.2f\n", En, Ikt4, U1vx);
                        TextQ = TextQ + String.format("U1vx при подключенному к выходу резистору на 2кОм =%.2f", U1vx);
                        ttl2QT.setText(TextQ);
                    } catch (Exception my) {
                        ttl2QT.setText("При расчетах появилась ошибка. Может СОПРОТИВЛЕНИЕ РАВНо О?\n");
                        return;
                    }


                }
            });

        }

    }
}
