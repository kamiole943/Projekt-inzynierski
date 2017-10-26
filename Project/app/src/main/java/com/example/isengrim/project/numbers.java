package com.example.isengrim.project;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;


public class numbers extends AppCompatActivity {


    Button ok, b1, b2, b3, b4, b5, b6, b7, b8, b9, bC;
    TextView generated_number, inform;
    ImageButton bvoice;
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
        final ImageButton bvoice = (ImageButton) findViewById(R.id.bvoice);

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
        bvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(steering==1) {
                    promptSpeechInput();
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

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Powiedz liczbę");
        try {
            startActivityForResult(intent, 100);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Wykrywanie mowy niewspomagane", Toast.LENGTH_LONG).show();
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TextView generated_number = (TextView) findViewById(R.id.generated_number);
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 100: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if( isValidInteger(result.get(0))) {
                        generated_number.setText(result.get(0));
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Liczba została nieprawnie wypowiedziana",Toast.LENGTH_SHORT).show();
                    }
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
    public static Boolean isValidInteger(String value) {
        try {
            Integer val = Integer.valueOf(value);
            if (val != null)
                return true;
            else
                return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
