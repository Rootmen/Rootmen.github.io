package com.example.rootmen.bcchelper.match;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rootmen.bcchelper.R;
import com.example.rootmen.bcchelper.match.MathsLibrary.EuclideanAlgorithm;
import com.example.rootmen.bcchelper.match.MathsLibrary.MillerRabinTest;

import static android.os.Build.VERSION_CODES.N;

public class Diof extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diof);
        Button Go = findViewById(R.id.Diof_g);
        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a, b;
                TextView qText = null;
                try {
                    qText = findViewById(R.id.Diof_qText);
                    a = Integer.parseInt(((EditText) findViewById(R.id.Diof_a)).getText().toString());
                    b = Integer.parseInt(((EditText) findViewById(R.id.Diof_b)).getText().toString());
                } catch (Exception my) {
                    assert qText != null;
                    qText.setText("Вы ввели неправильные данные");
                    return;
                }
                int e11 = 1, e12 = 0, e21 = 0, e22 = 1; // ед матрица Е=((1 0) (0 1)
                int E11, E12, E21, E22; // ячейки для замены при расчете умножения матриц
                if (((new EuclideanAlgorithm()).GetNod(a, b)).g != 1) {
                    qText.setText("Вы ввели неправильные данные");
                    return;
                }
                String Ansver = "";
                int q = a / b, r = a % b;
                a = b;
                b = r;
                while (r != 0) {
                    // Е=Е * ((0 1) (1 -q))
                    E11 = e12;
                    E21 = e22;
                    E12 = e11 - q * e12;
                    E22 = e21 - q * e22;
                    Ansver += "q = " + q + " r = " + r + " a = " + a + " b = " + b + "\n";
                    Ansver += "E = ((" + e11 + " " + e12 + ")(" + e21 + " " + e22 + "))*((0 1)(1 " + (-q) + "))\n";
                    // Вставка замены
                    e11 = E11;
                    e12 = E12;
                    e21 = E21;
                    e22 = E22;
                    Ansver += "E = ((" + e11 + " " + e12 + ")(" + e21 + " " + e22 + "))\n";
                    r = a % b;
                    q = a / b;
                    a = b;
                    b = r;
                }
                Ansver += "x= " + e12 + " y= " + e22;
                qText.setText(Ansver);
            }
        });
    }

}
