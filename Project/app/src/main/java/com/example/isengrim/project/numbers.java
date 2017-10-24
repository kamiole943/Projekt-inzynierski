package com.example.isengrim.project;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;


public class numbers extends AppCompatActivity {


    Button ok, b1, b2, b3, b4, b5, b6, b7, b8, b9, bC;
    TextView generated_number, inform;

    int steering = 0;
    int length = 3;
    String number = intarray2string(random_number(length));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);


        final Button ok = (Button) findViewById(R.id.ok);
        final TextView generated_number = (TextView) findViewById(R.id.generated_number);
        final TextView inform = (TextView) findViewById(R.id.inform);
        generated_number.setText(number);
        inform.setText("Rozgrywka została rozpoczeta. Proszę zapamiętać podaną liczbę.");
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(steering==0)
                {
                    ok.setText("Sprawdź");
                    generated_number.setText("");
                    inform.setText("Proszę wpisać zapamiętaną liczbę.");
                    steering=1;

                }

                else
                {
                    ok.setText("Gotów");
                    check(generated_number.getText().toString(),number);
                    number = intarray2string(random_number(length));

                    generated_number.setText(number);

                    steering=0;
                }
            }

        });
    }
    public void ButtonClicked(View view) {
        TextView generated_number = (TextView) findViewById(R.id.generated_number);
        if (steering==1) {
            switch (view.getId()) {
                case R.id.b1:
                    generated_number.append("1");
                    break;
                case R.id.b2:
                    generated_number.append("2");
                    break;
                case R.id.b3:
                    generated_number.append("3");
                    break;
                case R.id.b4:
                    generated_number.append("4");
                    break;
                case R.id.b5:
                    generated_number.append("5");
                    break;
                case R.id.b6:
                    generated_number.append("6");
                    break;
                case R.id.b7:
                    generated_number.append("7");
                    break;
                case R.id.b8:
                    generated_number.append("8");
                    break;
                case R.id.b9:
                    generated_number.append("9");
                    break;
                case R.id.b0:
                    generated_number.append("0");
                    break;
                case R.id.bC:
                    int length = generated_number.getText().length();
                    if (length > 0) {
                        generated_number.setText(generated_number.getText().toString().substring(0, generated_number.getText().length() - 1));
                    }
                    break;
            }
        }
        }

    public int[] random_number(int length)
    {
        int[] number = new int[length];

        for(int i=0;i<length;i++)
        {
            number[i]= (new Random().nextInt(10));

        }

        return number;
    }

    public String intarray2string(int[] table)
    {
        String str="";
        for (int i=0;i<table.length;i++) {
            str+=String.valueOf(table[i]);
        }
        return str;
    }

    public boolean check(String generated_number, String typed_number)
    {
        TextView inform = (TextView) findViewById(R.id.inform);
        if(generated_number.equals(typed_number)) {
            length++;
            inform.setText("Liczba została poprawnie zapamiętana, oto kolejna:");
            return true;
        }
        else
        {
            inform.setText("Liczba nie została poprawnie zapamiętana, oto kolejna:");
            return false;
        }
    }
}
