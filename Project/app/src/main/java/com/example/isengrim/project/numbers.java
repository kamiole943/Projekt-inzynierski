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


    Button ok;
    TextView generated_number;
    EditText write;
    int steering = 0;
    int length = 3;
    String number = intarray2string(random_number(length));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        final Button ok = (Button) findViewById(R.id.ok);
        final TextView generated_number = (TextView) findViewById(R.id.generated_number);
        final EditText write = (EditText) findViewById(R.id.write);
        generated_number.setText(number);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(steering==0)
                {
                    ok.setText("Send");
                    write.setVisibility(View.VISIBLE);
                    generated_number.setText("Type remembered number");

                    //wyciąganie klawiatury
                    try {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(write, InputMethodManager.SHOW_IMPLICIT);
                    } catch(Exception e){}
                    //
                    steering=1;

                }
                else
                {
                    ok.setText("Ready");

                    check(number,write.getText().toString());
                    //Chowanie klawiatury
                    try  {
                        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {}
                    //
                    write.setVisibility(View.GONE);
                    number = intarray2string(random_number(length));
                    generated_number.setText(number);
                    write.setText("");
                    steering=0;
                }
            }

        });
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
        if(generated_number.equals(typed_number)) {
            length++;
            return true;
        }
        else
        {
            return false;
        }
    }
}
