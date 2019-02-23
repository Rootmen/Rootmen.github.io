package com.example.rootmen.bcchelper.match;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rootmen.bcchelper.R;

public class FastPow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_pow);
        Button MainButton = findViewById(R.id.FP_go);
        MainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FastPowButtonLinester();
            }
        });
    }

    private void FastPowButtonLinester(){
        TextView qText=null;
        int Pow,Main,Mod;
        try{
            qText=findViewById(R.id.FP_text);
            Pow=Integer.parseInt(((EditText)findViewById(R.id.FP_pow)).getText().toString());
            Main=Integer.parseInt(((EditText)findViewById(R.id.FP_num)).getText().toString());
            Mod=Integer.parseInt(((EditText)findViewById(R.id.FP_mod)).getText().toString());
            if(Mod > 2147483646 || Mod < 1) throw new Exception();
        }catch (Exception my){
            assert qText != null;
            qText.setText("Вы ввели неправильные данные");
            return;
        }
        StringBuilder Answer = new StringBuilder();
        String BinaryMassPow = Integer.toBinaryString(Pow);
        int BinaryMassPowSize = BinaryMassPow.length();
        int Number = Main;
        Answer.append("1 | ").append(Number).append(" = ");
        Answer.append(Number);
        Number = Number % Mod;
        Answer.append(" = ").append(Number).append(" mod ").append(Mod).append("\n");
        for(int g = 1; g < BinaryMassPowSize; g++){
            if(BinaryMassPow.charAt(g)=='1'){
                Answer.append("1 | ").append(Number).append("^2 * ").append(Main).append(" = ");
                Number = Number * Number * Main;
                Answer.append(Number);
                Number = Number % Mod;
                Answer.append(" = ").append(Number).append(" mod ").append(Mod).append("\n");
            } else {
                Answer.append("0 | ").append(Number).append("^2 = ");
                Number = Number * Number;
                Answer.append(Number);
                Number = Number % Mod;
                Answer.append(" = ").append(Number).append(" mod ").append(Mod).append("\n");
            }
        }
        qText.setText(Answer.toString());
    }
}