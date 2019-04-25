package com.example.rootmen.bcchelper.match;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.rootmen.bcchelper.R;

public class
















DM extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dm);
        Button NOD = findViewById(R.id.NOD);
        NOD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DM.this, DM.NOD.class);
                startActivity(intent);
            }
        });
        Button NODret = findViewById(R.id.NODret);
        NODret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DM.this, DM.NODret.class);
                startActivity(intent);
            }
        });
        Button mtr = findViewById(R.id.Matrikc);
        mtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DM.this, DM.matriksObrt.class);
                startActivity(intent);
            }
        });
        Button Kit = findViewById(R.id.KitTeoryStart);
        Kit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DM.this, KitTeor.class);
                startActivity(intent);
            }
        });

        Button ObrtVcol = findViewById(R.id.ObratVcolce);
        ObrtVcol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DM.this, KolObrt.class);
                startActivity(intent);
            }
        });
        Button Fp = findViewById(R.id.FP_to_go);
        Fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DM.this, FastPow.class);
                startActivity(intent);
            }
        });
        Button MillerRabin = findViewById(R.id.MillerRabin_to_go);
        MillerRabin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DM.this, MillerRabin.class);
                startActivity(intent);
            }
        });
        Button Diof = findViewById(R.id.Diof_to_go);
        Diof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DM.this, Diof.class);
                startActivity(intent);
            }
        });

    }

    public static class NOD extends AppCompatActivity {

        public static String gcd(int a, int b) {
            StringBuilder Text = new StringBuilder("НОД(" + a + "," + b + ")\n");
            int g;
            while (a != b && a != 1 && b != 1) {
                if (a > b) {
                    Text.append(a);
                    for (g = 0; a > b && a != 1; g++) a = a - b;
                    Text.append("=").append(g).append("*").append(b).append("+").append(a).append("\n");
                } else {
                    Text.append(b);
                    for (g = 0; a < b && b != 1; g++) b = b - a;
                    Text.append("=").append(g).append("*").append(a).append("+").append(b).append("\n");
                }
            }
            return Text.toString();
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_nod);
            ((TextView) findViewById(R.id.NODq)).setText("НОД(A,B)");
            ((TextView) findViewById(R.id.NODq)).setMovementMethod(new ScrollingMovementMethod());
            Button NODret = findViewById(R.id.NODstart);
            NODret.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView NODq = findViewById(R.id.NODq);
                    int A, B;
                    try {
                        A = Math.abs(Integer.parseInt(((TextView) findViewById(R.id.Atext)).getText().toString()));
                        B = Math.abs(Integer.parseInt(((TextView) findViewById(R.id.Btext)).getText().toString()));
                        if (A == 0 || B == 0 || B == A) throw new Exception();
                    } catch (Exception my) {
                        NODq.setText("Неаправильные данные");
                        return;
                    }
                    NODq.setText(gcd(A, B));
                }
            });

        }
    }

    public static class NODret extends AppCompatActivity {
        boolean log = true;
        boolean log2 = true;
        String Qtext = "";
        int bg1 = 1, ag1 = 1;
        int a1s, b1s;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_nodret);
            ((TextView) findViewById(R.id.NODretq)).setText("Обратный ход для НОДА(A,B)\n");
            Button NODret = findViewById(R.id.NODretStart);
            NODret.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("CutPasteId")
                @Override
                public void onClick(View v) {
                    TextView NODretq = findViewById(R.id.NODretq);
                    NODretq.setMovementMethod(new ScrollingMovementMethod());
                    int A, B;
                    try {
                        A = Math.abs(Integer.parseInt(((TextView) findViewById(R.id.Arettext)).getText().toString()));
                        B = Math.abs(Integer.parseInt(((TextView) findViewById(R.id.Brettext)).getText().toString()));
                        if (A == 0 || B == 0 || B == A) throw new Exception();
                    } catch (Exception my) {
                        NODretq.setText("Неаправильные данные");
                        return;
                    }
                    gcdStart(A, B);
                    NODretq.setText(Qtext);
                }
            });
        }

        public void gcdStart(int A, int B) {
            ag1 = 1;
            bg1 = 1;
            log = false;
            log2 = true;
            Qtext = NOD.gcd(A, B);
            Qtext = Qtext + "Обратный ход\n";
            gcd(A, B);
            Qtext = Qtext + "\n\n\n\n";
        }

        public boolean gcd(int a, int b) {
            if(a<b) {
                int temp=a;
                a=b;
                b=temp;
            }
            if (b == 1||b==a||a==1) //если один из множителей равен 0
            {
                Qtext =  Qtext+b+""; ag1 = 1; bg1 = 0;
                a1s=a; b1s=b;
                return true;
            }
            int q = a / b;
            int r = a - q * b;
            a = b; b = r;
            boolean log3=gcd(a, b);
            if( log3||(log)){
                int temp = Math.abs(ag1);
                ag1 = -Math.abs(Math.abs(bg1) + Math.abs(ag1) * q);
                bg1 = temp;
                Qtext = Qtext + "=(" + ag1 + ")*" + a + "+(" + bg1 + ")*" + ((q * a)+r)+"\n";
                log=false;
                a1s=a; b1s=b;
            }else {
                int temp = Math.abs(ag1);
                ag1 = Math.abs(Math.abs(bg1) + Math.abs(ag1) * q);
                bg1 = -temp;
                Qtext = Qtext + "=(" + ag1 + ")*" + a + "+(" + bg1 + ")*" + ((q * a)+r)+"\n";
                if(!log) log=true;
                a1s=a; b1s=b;
            }
            return false;
        }
    }



    public static class matriksObrt extends AppCompatActivity {
        String Qtext;
        double[][] matrt;
        boolean Nozero1 = true, Nozero2 = true, Nozero3;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_matriks_obrt);
            Button StartMatr = findViewById(R.id.Go);
            ((TextView) findViewById(R.id.Textq)).setMovementMethod(new ScrollingMovementMethod());
            StartMatr.setOnClickListener(new View.OnClickListener() {
                @SuppressLint({"SetTextI18n", "DefaultLocale"})
                @Override
                public void onClick(View v) {
                    boolean log = ((CheckBox) findViewById(R.id.lokic)).isChecked();
                    TextView qt = findViewById(R.id.Textq);
                    boolean Nozero1 = true, Nozero2 = true, Nozero3 = true;
                    matrt = new double[4][4];
                    double[][] matrt2= new double[4][4];
                    matrt[1][1] = Integer.parseInt(((TextView) findViewById(R.id.m1)).getText().toString());
                    matrt[1][2] = Integer.parseInt(((TextView) findViewById(R.id.m2)).getText().toString());
                    matrt[1][3] = Integer.parseInt(((TextView) findViewById(R.id.m3)).getText().toString());
                    matrt[2][1] = Integer.parseInt(((TextView) findViewById(R.id.m4)).getText().toString());
                    matrt[2][2] = Integer.parseInt(((TextView) findViewById(R.id.m5)).getText().toString());
                    matrt[2][3] = Integer.parseInt(((TextView) findViewById(R.id.m6)).getText().toString());
                    matrt[3][1] = Integer.parseInt(((TextView) findViewById(R.id.m7)).getText().toString());
                    matrt[3][2] = Integer.parseInt(((TextView) findViewById(R.id.m8)).getText().toString());
                    matrt[3][3] = Integer.parseInt(((TextView) findViewById(R.id.m9)).getText().toString());
                    Qtext = "";
                    Del();
                    if(log){
                        for (int i = 1; i < 4; i++) {
                            for (int j = i+1; j < 4; j++) {
                                int temp = (int)matrt[i][j];
                                matrt[i][j] = matrt[j][i];
                                matrt[j][i] = temp;
                            }
                        }


                    }
                    Nozero1 = fLineMatr();
                    toEps();
                    Nozero2 = sLineMatr();
                    toEps();
                    fUpLine();
                    toEps();
                    Nozero3 = delAny(3);
                    toEps();
                    delAny(1);
                    toEps();
                    delAny(2);
                    toEps();
                    goTre();
                    toEps();
                    for (int L = 1; L < 4; L++)
                        Qtext += String.format("%.2f  %.2f  %.2f\n", matrt[L][1], matrt[L][2], matrt[L][3]);
                    Qtext +="Наш ответ\n";
                    String[] qote=new String[11];
                    int m = 0;
                    for(int L=1;L<4;L++) {
                        for (int k = 1; k < 4; k++) {
                            if (matrt[L][k] != 0)
                                qote[m] = String.format("%.2f*x" + k, matrt[L][k]);
                            else
                                qote[m] = "      ";
                            m++;
                        }
                    }

                        Qtext += qote[0]+"+"+qote[1]+"+"+qote[2]+"=0\n";
                        Qtext += qote[3]+"+"+qote[4]+"+"+qote[5]+"=0\n";
                        Qtext += qote[6]+"+"+qote[7]+"+"+qote[8]+"=0\n";
                    qt.setText("Определитель матрицы" + Qtext);
                    qt.setMovementMethod(new ScrollingMovementMethod());

                }
            });

        }

        @SuppressLint("DefaultLocale")
        protected boolean Del() {
            int opred = 0;
            opred = (int) (matrt[1][1] * (matrt[2][2] * matrt[3][3] - matrt[2][3] * matrt[3][2]) - matrt[1][2] * (matrt[2][1] * matrt[3][3] - matrt[2][3] * matrt[3][1]) + matrt[1][3] * (matrt[2][1] * matrt[3][2] - matrt[2][2] * matrt[3][1]));
            Qtext = Qtext + String.format("%.2f*(%.2f*%.2f-%.2f*%.2f)-%.2f(%.2f*%.2f-%.2f*%.2f)+%.2f(%.2f*%.2f-%.2f*%.2f)=%d", matrt[1][1], matrt[2][2], matrt[3][3], matrt[2][3], matrt[3][2], matrt[1][2], matrt[2][1], matrt[3][3], matrt[2][3], matrt[3][1], matrt[1][3], matrt[2][1], matrt[3][2], matrt[2][2], matrt[3][1], opred);
            return opred == 0;
        }

        @SuppressLint("DefaultLocale")
        protected boolean fLineMatr() {
            if (matrt[1][1] != 0) {

                if (matrt[2][1] != 0) {
                    Qtext += String.format("\nСтрока 2 умножается на а11/а21=%.0f/%.0f и к ней прибавляеться строка 1\n", matrt[1][1], matrt[2][1]);

                    matrt[2][2] = ((-matrt[1][1] / matrt[2][1]) * matrt[2][2] + matrt[1][2]);
                    matrt[2][3] = ((-matrt[1][1] / matrt[2][1]) * matrt[2][3] + matrt[1][3]);
                    matrt[2][1] = ((-matrt[1][1] / matrt[2][1]) * matrt[2][1] + matrt[1][1]);

                    for (int g = 1; g < 4; g++)
                        Qtext += String.format("%.2f  %.2f  %.2f\n", matrt[g][1], matrt[g][2], matrt[g][3]);
                }
                if (matrt[3][1] != 0) {
                    Qtext += String.format("Строка 3 умножается на а11/а31=%.0f/%.0f и к ней прибавляеться строка 1\n", matrt[1][1], matrt[3][1]);

                    matrt[3][2] = ((-matrt[1][1] / matrt[3][1]) * matrt[3][2] + matrt[1][2]);
                    matrt[3][3] = ((-matrt[1][1] / matrt[3][1]) * matrt[3][3] + matrt[1][3]);
                    matrt[3][1] = ((-matrt[1][1] / matrt[3][1]) * matrt[3][1] + matrt[1][1]);

                    for (int g = 1; g < 4; g++)
                        Qtext += String.format("%.2f  %.2f  %.2f\n", matrt[g][1], matrt[g][2], matrt[g][3]);
                }
            } else {
                if (matrt[1][2] != 0) {

                    if (matrt[2][2] != 0) {
                        Qtext += String.format("\nСтрока 2 умножается на а12/а22=%.0f/%.0f и к ней прибавляеться строка 1\n", matrt[1][2], matrt[2][2]);

                        matrt[2][1] = ((-matrt[1][2] / matrt[2][2]) * matrt[2][1] + matrt[1][1]);
                        matrt[2][3] = ((-matrt[1][2] / matrt[2][2]) * matrt[2][3] + matrt[1][3]);
                        matrt[2][2] = ((-matrt[1][2] / matrt[2][2]) * matrt[2][2] + matrt[1][2]);
                        for (int g = 1; g < 4; g++)
                            Qtext += String.format("%.2f  %.2f  %.2f\n", matrt[g][1], matrt[g][2], matrt[g][3]);

                        Qtext += String.format("Строка 3 умножается на а12/а32=%.0f/%.0f и к ней прибавляеться строка 1\n", matrt[1][2], matrt[3][2]);
                    }
                    if (matrt[3][2] != 0) {
                        matrt[3][1] = ((-matrt[1][2] / matrt[3][2]) * matrt[3][1] + matrt[1][1]);
                        matrt[3][3] = ((-matrt[1][2] / matrt[3][2]) * matrt[3][3] + matrt[1][3]);
                        matrt[3][2] = ((-matrt[1][2] / matrt[3][2]) * matrt[3][2] + matrt[1][2]);

                        for (int g = 1; g < 4; g++)
                            Qtext += String.format("%.2f  %.2f  %.2f\n", matrt[g][1], matrt[g][2], matrt[g][3]);
                    }
                } else {
                    if (matrt[1][3] != 0) {
                        if (matrt[2][2] != 0) {
                            Qtext += String.format("2 умножается на а13/а23=%.0f/%.0f и к ней прибавляеться строка 1\n", matrt[1][3], matrt[2][3]);

                            matrt[2][1] = ((-matrt[1][3] / matrt[2][3]) * matrt[2][1] + matrt[1][1]);
                            matrt[2][2] = ((-matrt[1][3] / matrt[2][3]) * matrt[2][2] + matrt[1][2]);
                            matrt[2][3] = ((-matrt[1][3] / matrt[2][3]) * matrt[2][3] + matrt[1][3]);


                            for (int g = 1; g < 4; g++)
                                Qtext += String.format("%.2f  %.2f  %.2f\n", matrt[g][1], matrt[g][2], matrt[g][3]);
                        }

                        Qtext += String.format("Строка 3 умножается на а13/а33=%.0f/%.0f и к ней прибавляеться строка 1\n", matrt[1][3], matrt[3][3]);

                        matrt[3][1] = ((-matrt[1][3] / matrt[3][3]) * matrt[3][1] + matrt[1][1]);
                        matrt[3][2] = ((-matrt[1][3] / matrt[3][3]) * matrt[3][2] + matrt[1][2]);
                        matrt[3][3] = ((-matrt[1][3] / matrt[3][3]) * matrt[3][3] + matrt[1][3]);


                        for (int g = 1; g < 4; g++)
                            Qtext += String.format("%.2f  %.2f  %.2f\n", matrt[g][1], matrt[g][2], matrt[g][3]);
                    } else {
                        Qtext += String.format("Все элементы первой строки равны 0, переходим ко 2 строке");
                        return false;
                    }
                }
            }
            return true;
        }

        @SuppressLint("DefaultLocale")
        protected boolean sLineMatr() {
            if (matrt[3][1] != 0 && matrt[2][1] != 0) {

                Qtext += String.format("\nСтрока 3 умножается на а21/а31=%.0f/%.0f и к ней прибавляеться строка 2\n", matrt[2][1], matrt[3][1]);

                matrt[3][2] = ((-matrt[2][1] / matrt[3][1]) * matrt[3][2] + matrt[2][2]);
                matrt[3][3] = ((-matrt[2][1] / matrt[3][1]) * matrt[3][3] + matrt[2][3]);
                matrt[3][1] = ((-matrt[2][1] / matrt[3][1]) * matrt[3][1] + matrt[2][1]);

                for (int g = 1; g < 4; g++)
                    Qtext += String.format("%.2f  %.2f  %.2f\n", matrt[g][1], matrt[g][2], matrt[g][3]);
                return true;
            }
            if (matrt[3][2] != 0 && matrt[2][2] != 0) {

                Qtext += String.format("\nСтрока 3 умножается на а22/а32=%.0f/%.0f и к ней прибавляеться строка 2\n", matrt[2][2], matrt[3][2]);

                matrt[3][1] = ((-matrt[2][2] / matrt[3][2]) * matrt[3][1] + matrt[2][1]);
                matrt[3][3] = ((-matrt[2][2] / matrt[3][2]) * matrt[3][3] + matrt[2][3]);
                matrt[3][2] = ((-matrt[2][2] / matrt[3][2]) * matrt[3][2] + matrt[2][2]);

                for (int g = 1; g < 4; g++)
                    Qtext += String.format("%.2f  %.2f  %.2f\n", matrt[g][1], matrt[g][2], matrt[g][3]);
                return true;
            }
            if (matrt[3][3] != 0 && matrt[2][3] != 0) {

                Qtext += String.format("\nСтрока 3 умножается на а23/а33=%.0f/%.0f и к ней прибавляеться строка 2\n", matrt[2][3], matrt[3][3]);

                matrt[3][1] = ((-matrt[2][3] / matrt[3][3]) * matrt[3][1] + matrt[2][1]);
                matrt[3][2] = ((-matrt[2][3] / matrt[3][3]) * matrt[3][2] + matrt[2][2]);
                matrt[3][3] = ((-matrt[2][3] / matrt[3][3]) * matrt[3][3] + matrt[2][3]);

                for (int g = 1; g < 4; g++)
                    Qtext += String.format("%.2f  %.2f  %.2f\n", matrt[g][1], matrt[g][2], matrt[g][3]);
                return true;
            }
            Qtext += String.format("Сложить 2 и 3 строку нельзя");
            return false;
        }

        @SuppressLint("DefaultLocale")
        protected boolean delAny(int g) {
            int u=0, l=0;
            if (g == 1) {
                u = 2;
                l = 3;
            }
            if (g == 2) {
                u = 1;
                l = 3;
            }
            if (g == 3) {
                u = 1;
                l = 2;
            }
            if (matrt[g][2] == 0 && matrt[g][3] == 0 && matrt[g][1] != 0) {

                if (matrt[u][1] != 0) {
                    Qtext += String.format("Вычитаем из строки %d умноженную на умноженную на а%d1/a%d1=%.2f/%.2f строку %d\n",  l,g, u,  matrt[g][1], matrt[u][1],g);

                    matrt[u][1] = ((-matrt[g][1] / matrt[u][1]) * matrt[u][1] + matrt[g][1]);
                    matrt[u][2] = ((-matrt[g][1] / matrt[u][1]) * matrt[u][2] + matrt[g][2]);
                    matrt[u][3] = ((-matrt[g][1] / matrt[u][1]) * matrt[u][3] + matrt[g][3]);
                    for (int p = 1; p < 4; p++)
                        Qtext += String.format("%.2f  %.2f  %.2f\n", matrt[p][1], matrt[p][2], matrt[p][3]);
                }
                if (matrt[l][1] != 0) {
                    Qtext += String.format("Вычитаем из строки %d умноженную на умноженную на а%d1/a%d1=%.2f/%.2f строку %d\n", l, g, l, matrt[g][1], matrt[l][1],g);

                    matrt[l][1] = ((-matrt[g][1] / matrt[l][1]) * matrt[l][1] + matrt[g][1]);
                    matrt[l][2] = ((-matrt[g][1] / matrt[l][1]) * matrt[l][2] + matrt[g][2]);
                    matrt[l][3] = ((-matrt[g][1] / matrt[l][1]) * matrt[l][3] + matrt[g][3]);
                    for (int p = 1; p < 4; p++)
                        Qtext += String.format("%.2f  %.2f  %.2f\n", matrt[p][1], matrt[p][2], matrt[p][3]);
                }
                return true;
            }
            if (matrt[g][1] == 0 && matrt[g][3] == 0 && matrt[g][2] != 0) {

                if (matrt[u][2] != 0) {
                    Qtext += String.format("Вычитаем из строки %d умноженную на умноженную на а%d2/a%d2=%.2f/%.2f строку %d1\n", l, g, u,  matrt[g][2], matrt[u][2],g);

                    matrt[u][1] = ((-matrt[g][2] / matrt[u][2]) * matrt[u][1] + matrt[g][1]);
                    matrt[u][3] = ((-matrt[g][2] / matrt[u][2]) * matrt[u][3] + matrt[g][3]);
                    matrt[u][2] = ((-matrt[g][2] / matrt[u][2]) * matrt[u][2] + matrt[g][2]);
                    for (int p = 1; p < 4; p++)
                        Qtext += String.format("%.2f  %.2f  %.2f\n", matrt[p][1], matrt[p][2], matrt[p][3]);
                }
                if (matrt[l][2] != 0) {
                    Qtext += String.format("Вычитаем из строки %d умноженную на умноженную на а%d2/a%d2=%.2f/%.2f строку %d1\n", l, g, l, matrt[g][2], matrt[l][2],g);

                    matrt[l][1] = ((-matrt[g][2] / matrt[l][2]) * matrt[l][1] + matrt[g][1]);
                    matrt[l][3] = ((-matrt[g][2] / matrt[l][2]) * matrt[l][3] + matrt[g][3]);
                    matrt[l][2] = ((-matrt[g][2] / matrt[l][2]) * matrt[l][2] + matrt[g][2]);
                    for (int p = 1; p < 4; p++)
                        Qtext += String.format("%.2f  %.2f  %.2f\n", matrt[p][1], matrt[p][2], matrt[p][3]);
                }

                return true;
            }
            if (matrt[g][1] == 0 && matrt[g][2] == 0 && matrt[g][3] != 0) {

                if (matrt[u][3] != 0) {
                    Qtext += String.format("Вычитаем из строки %d умноженную на умноженную на а%d3/%d3=%.2f/%.2f строку %d\n", l, g, u,  matrt[g][3], matrt[u][3],g);

                    matrt[u][1] = ((-matrt[g][3] / matrt[u][3]) * matrt[u][1] + matrt[g][1]);
                    matrt[u][2] = ((-matrt[g][3] / matrt[u][3]) * matrt[u][2] + matrt[g][2]);
                    matrt[u][3] = ((-matrt[g][3] / matrt[u][3]) * matrt[u][3] + matrt[g][3]);
                    for (int p = 1; p < 4; p++)
                        Qtext += String.format("%.2f  %.2f  %.2f\n", matrt[p][1], matrt[p][2], matrt[p][3]);
                }
                if (matrt[l][3] != 0) {
                    Qtext += String.format("Вычитаем из строки %d умноженную на умноженную на а%d3/%d3=%.2f/%.2f строку %d\n", l, g, l,  matrt[g][3], matrt[l][3],g);

                    matrt[l][1] = ((-matrt[g][3] / matrt[l][3]) * matrt[l][1] + matrt[g][1]);
                    matrt[l][2] = ((-matrt[g][3] / matrt[l][3]) * matrt[l][2] + matrt[g][2]);
                    matrt[l][3] = ((-matrt[g][3] / matrt[l][3]) * matrt[l][3] + matrt[g][3]);
                    for (int p = 1; p < 4; p++)
                        Qtext += String.format("%.2f  %.2f  %.2f\n", matrt[p][1], matrt[p][2], matrt[p][3]);
                }

                return true;
            }
            return true;
        }

        private void goTre() {
            Qtext += "Приведем матрицу к единичному виду\n";
            if (Nozero1)
                if (matrt[1][1] != 0) {
                    matrt[1][2] = matrt[1][2] / matrt[1][1];
                    matrt[1][3] = matrt[1][3] / matrt[1][1];
                    matrt[1][1] = matrt[1][1] / matrt[1][1];
                } else if (matrt[1][2] != 0) {
                    matrt[1][1] = matrt[1][1] / matrt[1][2];
                    matrt[1][3] = matrt[1][3] / matrt[1][2];
                    matrt[1][2] = matrt[1][2] / matrt[1][2];
                } else if (matrt[1][3] != 0) {
                    matrt[1][1] = matrt[1][1] / matrt[1][3];
                    matrt[1][2] = matrt[1][2] / matrt[1][3];
                    matrt[1][3] = matrt[1][3] / matrt[1][3];
                }
            if (Nozero2)
                if (matrt[2][1] != 0) {
                    matrt[2][2] = matrt[2][2] / matrt[2][1];
                    matrt[2][3] = matrt[2][3] / matrt[2][1];
                    matrt[2][1] = matrt[2][1] / matrt[2][1];
                } else if (matrt[2][2] != 0) {
                    matrt[2][1] = matrt[2][1] / matrt[2][2];
                    matrt[2][3] = matrt[2][3] / matrt[2][2];
                    matrt[2][2] = matrt[2][2] / matrt[2][2];
                } else if (matrt[2][3] != 0) {
                    matrt[2][1] = matrt[2][1] / matrt[2][3];
                    matrt[2][2] = matrt[2][2] / matrt[2][3];
                    matrt[2][3] = matrt[2][3] / matrt[2][3];
                }

            if (matrt[3][1] != 0) {
                matrt[3][2] = matrt[3][2] / matrt[3][1];
                matrt[3][3] = matrt[3][3] / matrt[3][1];
                matrt[3][1] = matrt[3][1] / matrt[3][1];
            } else if (matrt[3][2] != 0) {
                matrt[3][1] = matrt[3][1] / matrt[3][2];
                matrt[3][3] = matrt[3][3] / matrt[3][2];
                matrt[3][2] = matrt[3][2] / matrt[3][2];
            } else if (matrt[3][3] != 0) {
                matrt[3][1] = matrt[3][1] / matrt[3][3];
                matrt[3][2] = matrt[3][2] / matrt[3][3];
                matrt[3][3] = matrt[3][3] / matrt[3][3];
            }
        }

        private void toEps() {
            for(int g=1;g<4;g++)
                for(int k=1;k<4;k++)
                    if(matrt[g][k]<=0.00001&&matrt[g][k]>=-0.00001)
                        matrt[g][k]=0;
        }

        @SuppressLint("DefaultLocale")
        private void fUpLine(){

            if (matrt[1][1] != 0 && matrt[2][1] != 0) {

                Qtext += String.format("\nСтрока 1 умножается на а21/а31=%.0f/%.0f и к ней прибавляеться строка 2\n", matrt[2][1], matrt[1][1]);

                matrt[1][2] = ((-matrt[2][1] / matrt[1][1]) * matrt[1][2] + matrt[2][2]);
                matrt[1][3] = ((-matrt[2][1] / matrt[1][1]) * matrt[1][3] + matrt[2][3]);
                matrt[1][1] = ((-matrt[2][1] / matrt[1][1]) * matrt[1][1] + matrt[2][1]);

                for (int g = 1; g < 4; g++)
                    Qtext += String.format("%.2f  %.2f  %.2f\n", matrt[g][1], matrt[g][2], matrt[g][3]);

            }
            if (matrt[1][2] != 0 && matrt[2][2] != 0) {

                Qtext += String.format("\nСтрока 1 умножается на а22/а32=%.0f/%.0f и к ней прибавляеться строка 2\n", matrt[2][2], matrt[1][2]);

                matrt[1][1] = ((-matrt[2][2] / matrt[1][2]) * matrt[1][1] + matrt[2][1]);
                matrt[1][3] = ((-matrt[2][2] / matrt[1][2]) * matrt[1][3] + matrt[2][3]);
                matrt[1][2] = ((-matrt[2][2] / matrt[1][2]) * matrt[1][2] + matrt[2][2]);

                for (int g = 1; g < 4; g++)
                    Qtext += String.format("%.2f  %.2f  %.2f\n", matrt[g][1], matrt[g][2], matrt[g][3]);

            }
            if (matrt[1][3] != 0 && matrt[2][3] != 0) {

                Qtext += String.format("\nСтрока 1 умножается на а23/а33=%.0f/%.0f и к ней прибавляеться строка 2\n", matrt[2][3], matrt[1][3]);

                matrt[1][1] = ((-matrt[2][3] / matrt[1][3]) * matrt[1][1] + matrt[2][1]);
                matrt[1][2] = ((-matrt[2][3] / matrt[1][3]) * matrt[1][2] + matrt[2][2]);
                matrt[1][3] = ((-matrt[2][3] / matrt[1][3]) * matrt[1][3] + matrt[2][3]);

                for (int g = 1; g < 4; g++)
                    Qtext += String.format("%.2f  %.2f  %.2f\n", matrt[g][1], matrt[g][2], matrt[g][3]);

            }
            Qtext += String.format("Сложить 2 и 1 строку нельзя");

           }
        }
    }
