package com.example.isengrim.project;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.opencsv.CSVWriter;

public class Numbers extends AppCompatActivity {


    int steering = 0, length, chances=0, voice=0, seconds=0 ,minutes=0, milliseconds=0;
    boolean remembered=false;
    String number, time[]={"","",""}, Voicenumber;
    Timer timer = new Timer();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_numbers);
        Get_Config();
        number = intarray2string(random_number(length));

        final Button ok = (Button) findViewById(R.id.ok);
        final TextView generated_number = (TextView) findViewById(R.id.generated_number);
        generated_number.setMovementMethod(new ScrollingMovementMethod());

        final TextView inform = (TextView) findViewById(R.id.inform);
        final ImageButton bvoice = (ImageButton) findViewById(R.id.bvoice);

        generated_number.setText(number);
        ivisibility(true);

        inform.setText("Rozgrywka została rozpoczeta. Proszę zapamiętać podaną sekwencję cyfr.");
        Timer();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generated_number.scrollTo(0,0);
                if(steering==0)
                {
                    ok.setText("Dalej");
                    generated_number.setText("");
                    inform.setText("Proszę wpisać zapamiętany ciąg cyfr.");
                    ivisibility(false);
                    steering=1;
                    time[1]=time[0];
                    seconds=0;
                    minutes=0;
                    milliseconds=0;
                    timer.cancel();
                    timer.purge();





                }

                else if(steering==1)
                {


                    ok.setText("Gotów");
                    check(generated_number.getText().toString(),number);
                    number = intarray2string(random_number(length));


                   ivisibility(true);
                    steering=0;
                    if(chances>0) {
                        generated_number.setText(number);
                        Timer();

                    }
                    else
                    {
                        ok.setText("Zakończ");
                        steering=2;
                        generated_number.setGravity(Gravity.TOP);
                        generated_number.setText("");
                        generated_number.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                        if(remembered) {
                           if(length-1==1) generated_number.append("Najdłuży zapamiętany ciąg miał długość " + (length - 1) + " cyfry.\n");
                            else generated_number.append("Najdłuży zapamiętany ciąg miał długość " + (length - 1) + " cyfr.\n");
                            generated_number.append("Został zapamiętany w czasie: " + time[2]);
                            SaveToCSV(String.valueOf(length-1), time[2]);



                        }
                        else generated_number.append("Niestety nie udało się zapamiętać żadnego ciągu cyfr.");

                    }

                }
                else
                {
                    finish();
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
            final int scrollAmount = generated_number.getLayout().getLineTop(generated_number.getLineCount()) - generated_number.getHeight();
            // if there is no need to scroll, scrollAmount will be <=0
            if (scrollAmount > 0)
                generated_number.scrollTo(0, scrollAmount);
            else
                generated_number.scrollTo(0, 0);
        }
        }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

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
                    Log.e("result",result.get(0).toString());
                        Voicenumber="";
                    for(int i=0; i<result.get(0).toString().length();i++)
                    {
                        if(isValidInteger(result.get(0).toString().charAt(i)))
                        {
                            Voicenumber+=result.get(0).toString().charAt(i);
                        }
                    }

                   if(Voicenumber != "")
                   {
                        if(voice==1)
                        generated_number.setText(Voicenumber);
                        else
                        {
                            generated_number.append(Voicenumber);

                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Ciąg nie został poprawnie wypowiedziany",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            }

        }
    }


    public static int[] random_number(int length)
    {
        int[] number = new int[length];

        for(int i=0;i<length;i++)
        {
            number[i]= (new Random().nextInt(10));

        }

        return number;
    }

    public static String intarray2string(int[] table)
    {
        String str="";
        for (int i=0;i<table.length;i++) {
            str+=String.valueOf(table[i]);
        }
        return str;
    }

    protected void check(String generated_number, String typed_number)
    {
        TextView inform = (TextView) findViewById(R.id.inform);
        if(generated_number.equals(typed_number)) {
            length++;
            time[2]=time[1];
            inform.setText("Ciąg został poprawnie zapamiętany, oto kolejny:");
            remembered=true;

        }
        else
        {

            chances--;
            if(chances>0) inform.setText("Ciąg nie został poprawnie zapamiętany. Pozostała liczba szans: " +chances +"\nOto kolejny ciąg: ");

            else
            {
                inform.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                inform.setTextSize(TypedValue.COMPLEX_UNIT_SP,40);
                inform.setText("Koniec gry.");
            }

        }
    }
    protected static Boolean isValidInteger(Character value) {
        try {
            if(Character.isDigit(value))
                    return true;
                else
                    return false;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    protected void ivisibility(boolean off)
    {


        float d = getApplicationContext().getResources().getDisplayMetrics().density;
        TextView generated_number = (TextView) findViewById(R.id.generated_number);
        ImageButton Voice = (ImageButton)findViewById(R.id.bvoice);
        Button C = (Button)findViewById(R.id.bC);
        Button ok = (Button) findViewById(R.id.ok);
        RelativeLayout.LayoutParams pok = (RelativeLayout.LayoutParams)ok.getLayoutParams();
        RelativeLayout.LayoutParams pvc = (RelativeLayout.LayoutParams)Voice.getLayoutParams();
        RelativeLayout.LayoutParams pgn = (RelativeLayout.LayoutParams)generated_number.getLayoutParams();
        if(off) {
            for (int i = 0; i<= 9; i++) {
                int id = getResources().getIdentifier("b" + i, "id", getPackageName());
                Button tmp = (Button) findViewById(id);
                tmp.setVisibility(View.INVISIBLE);

            }

            C.setVisibility(View.INVISIBLE);
            Voice.setVisibility(View.INVISIBLE);
            generated_number.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            generated_number.setHeight(500);
            pok.leftMargin = 0;
            ok.setLayoutParams(pok);
            pgn.bottomMargin = (int)d*150;
            generated_number.setLayoutParams(pgn);
        }
        else {
            for (int i = 0; i <= 9; i++) {
                int id = getResources().getIdentifier("b" + i, "id", getPackageName());
                Button tmp = (Button) findViewById(id);
                tmp.setVisibility(View.VISIBLE);


            }
            C.setVisibility(View.VISIBLE);
            Voice.setVisibility(View.VISIBLE);
            generated_number.setGravity(Gravity.TOP);
            pok.leftMargin = pvc.leftMargin;
            ok.setLayoutParams(pok);
            pgn.bottomMargin = (int)d*300; // in PX
            generated_number.setLayoutParams(pgn);

        }
    }

    public void Get_Config()
    {
        String filename = "Number_Config.txt";
        String filepath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + filename;
        File file = new File(filepath);

        if (!file.exists())
        {
            try {
                FileWriter writer = new FileWriter(file,true);
                writer.write("3|");
                writer.write("3|");
                writer.write("1");
                writer.close();
            }
            catch (IOException e) {
                Log.e("Exception", "File write failed: " + e.toString());
            }
        }
        // StringBuilder setup = new StringBuilder();
        try {
            //FileReader reader = new FileReader(file);
            //setup.append(reader.read());
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder setup = new StringBuilder();
            setup.append(reader.readLine());
            //Make sure you close all streams.
            fis.close();
            String[] separated = setup.toString().split("\\|");
            length=Integer.parseInt(separated[0]);
            chances=Integer.parseInt(separated[1]);
            voice=Integer.parseInt(separated[2]);
        }
        catch (IOException e) {
            Log.e("Exception", "File read failed: " + e.toString());
        }
    }

    public void SaveToCSV(String Data1, String Data2) {
        String csv = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = "Number_Records.csv";
        String filePath = csv + File.separator + fileName;
        File file = new File(filePath);
        CSVWriter writer;

        DateFormat df = new SimpleDateFormat("d.MM.yyyy, HH:mm");
        String mydate = df.format(Calendar.getInstance().getTime());

        try {
            if (file.exists() && !file.isDirectory()) {
                writer = new CSVWriter(new FileWriter(filePath, true), ';');
            } else {
                writer = new CSVWriter(new FileWriter(filePath), ';');
            }
            String[] data = {mydate, Data1, Data2};
            writer.writeNext(data);
            writer.close();
        } catch (Exception e) {
            Log.i("", "Something went wrong");
            Log.e("", e.getMessage());
        }
    }


    public void Timer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        milliseconds++;
                     if(milliseconds==100) {
                         seconds ++;
                         if (seconds == 60) {
                             minutes ++;
                             seconds = 0;
                         }
                         milliseconds= 0;
                     }
                        time[0] = String.format(String.format("%02d", minutes) + ":" + String.format("%02d", seconds)+ "." + String.format("%02d",milliseconds));
                    }
                });
            }
        },0,10);
    }

}
