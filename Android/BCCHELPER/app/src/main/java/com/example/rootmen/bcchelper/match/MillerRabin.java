package com.example.rootmen.bcchelper.match;

import android.content.Intent;
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
import com.example.rootmen.bcchelper.match.DM;
import com.example.rootmen.bcchelper.match.FastPow;
import com.example.rootmen.bcchelper.match.MathsLibrary.MillerRabinTest;

public class MillerRabin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miller_rabin);
        Button Go = findViewById(R.id.MillerRabin_Go);
        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a, N;
                TextView qText = null;
                try {
                    qText = findViewById(R.id.MillerRabin_Qtext);
                    a = Integer.parseInt(((EditText) findViewById(R.id.MillerRabin_a)).getText().toString());
                    N = Integer.parseInt(((EditText) findViewById(R.id.MillerRabin_N)).getText().toString());
                } catch (Exception my) {
                    assert qText != null;
                    qText.setText("Вы ввели неправильные данные");
                    return;
                }
                qText.setText(MillerRabinTest.getMillerRabinTestString(a, N));
            }
        });
    }
}
