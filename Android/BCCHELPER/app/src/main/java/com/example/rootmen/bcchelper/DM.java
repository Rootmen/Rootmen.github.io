package com.example.rootmen.bcchelper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class DM extends AppCompatActivity {

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
    }

    public static class NOD extends AppCompatActivity {

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

        public static String gcd(int a, int b) {
            String Text = "НОД(" + a + "," + b + ")\n";
            int g;
            while (a != b && a != 1 && b != 1) {
                if (a > b) {
                    Text = Text + a;
                    for (g = 0; a > b && a != 1 && b != 1; g++) a = a - b;
                    Text = Text + "=" + g + "*" + b + "+" + a + "\n";
                } else {
                    Text = Text + b;
                    for (g = 0; a < b && a != 1 && b != 1; g++) b = b - a;
                    Text = Text + "=" + g + "*" + a + "+" + b + "\n";
                }
            }
            return Text;
        }
    }

    public static class NODret extends AppCompatActivity {
        String Qtext = "";
        int bg1 = 1, ag1 = 1;
        static boolean log = true;
        static boolean log2 = true;

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
                    ag1 = 1;
                    bg1 = 1;
                    Qtext = NOD.gcd(A, B);
                    Qtext = Qtext + "\n\n\n\n";
                    gcd(A, B);
                    NODretq.setText(Qtext);
                }
            });
        }

        public int gcd(int a, int b) {
            int a1 = a, b1 = b;
            int g;
            if (a != b && a != 1 && b != 1) {
                if (a > b) {
                    for (g = 0; a > b; g++)
                        a = a - b;
                    int g1 = gcd(a, b);
                    bg1 = ag1 * g + bg1;
                    if (log2) {
                        Qtext = Qtext + "=" + bg1 + "*" + b + "-" + ag1 + "*" + a1 + "\n";
                        log2 = false;
                        log = true;
                        return g;
                    }
                    if (log && !log2) {
                        log = false;
                        log2 = true;
                    }
                    Qtext = Qtext + "=" + ag1 + "*" + a1 + "-" + bg1 + "*" + b + "\n";
                    return g;
                } else {
                    for (g = 0; a < b; g++)
                        b = b - a;
                    int g1 = gcd(a, b);
                    ag1 = bg1 * g + ag1;
                    if (log2) {
                        Qtext = Qtext + "=" + ag1 + "*" + a + "-" + bg1 + "*" + b1 + "\n";
                        log2 = false;
                        log = true;
                        return g;
                    }
                    if (log && !log2) {
                        log = false;
                        log2 = true;
                    }
                    Qtext = Qtext + "=" + bg1 + "*" + b1 + "-" + ag1 + "*" + a + "\n";
                    return g;
                }
            } else {
                if (a < b) {
                    Qtext = Qtext + a;
                    bg1 = 0;
                } else {
                    Qtext = Qtext + b;
                    ag1 = 0;
                }
                log = true;
                return 1;
            }

        }
    }


    public static class matriksObrt extends AppCompatActivity {
        String Qtext;
        int[][]matrt;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_matriks_obrt);
            Button StartMatr = findViewById(R.id.Go);
            StartMatr.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    boolean log = ((CheckBox) findViewById(R.id.lokic)).isChecked();
                    TextView qt = findViewById(R.id.Textq);
                    matrt=new int[3][3];
                    matrt[0][0]= Integer.parseInt(((TextView)findViewById(R.id.m1)).getText().toString());
                    matrt[0][1]= Integer.parseInt(((TextView)findViewById(R.id.m2)).getText().toString());
                    matrt[0][2]= Integer.parseInt(((TextView)findViewById(R.id.m3)).getText().toString());
                    matrt[1][0]= Integer.parseInt(((TextView)findViewById(R.id.m4)).getText().toString());
                    matrt[1][1]= Integer.parseInt(((TextView)findViewById(R.id.m5)).getText().toString());
                    matrt[1][2]= Integer.parseInt(((TextView)findViewById(R.id.m6)).getText().toString());
                    matrt[2][0]= Integer.parseInt(((TextView)findViewById(R.id.m7)).getText().toString());
                    matrt[2][1]= Integer.parseInt(((TextView)findViewById(R.id.m8)).getText().toString());
                    matrt[2][2]= Integer.parseInt(((TextView)findViewById(R.id.m9)).getText().toString());
                    Qtext="";
                    Del();
                    qt.setText("Пока-что только определитель матрицы"+Qtext);

                }
            });

        }

        @SuppressLint("DefaultLocale")
        protected void Del() {
            int opred=0;
            opred=(matrt[1][1]*(matrt[2][2]*matrt[3][3]-matrt[2][3]*matrt[3][2])-matrt[1][2]*(matrt[2][1]*matrt[3][3]-matrt[2][3]*matrt[3][1])+matrt[1][3]*(matrt[2][1]*matrt[3][2]-matrt[2][2]*matrt[3][1]));
            Qtext=Qtext+String.format("%d*(%d*%d-%d*%d)-%d(%d*%d-%d*%d)+%d(%d*%d-%d*%d)=%d",matrt[1][1],matrt[2][2],matrt[3][3],matrt[2][3],matrt[3][2],matrt[1][2],matrt[2][1],matrt[3][3],matrt[2][3],matrt[3][1],matrt[1][3],matrt[2][1],matrt[3][2],matrt[2][2],matrt[3][1],opred);
        }
    }

}




