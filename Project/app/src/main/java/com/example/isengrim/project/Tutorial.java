

package com.example.isengrim.project;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;


public class Tutorial extends numbers {



        int steering = 0;

        int step = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_numbers);


            final Button ok = (Button) findViewById(R.id.ok);
            final TextView generated_number = (TextView) findViewById(R.id.generated_number);
            generated_number.setMovementMethod(new ScrollingMovementMethod());
            final TextView inform = (TextView) findViewById(R.id.inform);
            inform.setMovementMethod(new ScrollingMovementMethod());
            inform.setTextSize(18);
            final ImageButton bvoice = (ImageButton) findViewById(R.id.bvoice);
            final ImageButton Voice = (ImageButton)findViewById(R.id.bvoice);


            ivisibility(true);

            inform.setText("Oto samouczek, w którym nauczysz się jak wygląda rozgrywka. Rozpocznie się naciskając przycisk poniżej.");
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inform.scrollTo(0,0);
                switch(step)
                {
                    case 0:
                        generated_number.setText("123");
                        inform.setText("Wygenerowany został ciąg cyfr ''123''. Postaraj się go zapamiętać a następnie kliknąć w przycisk wyrażający gotowość, znajdujący się poniżej. ");
                        step=1;
                        break;
                    case 1:
                        steering=setup(steering);
                            inform.setText("Proszę nacisnąć na przyciski w odpowiedniej kolejności, wpisująć zapamiętaną liczbę.");
                        Voice.setVisibility(View.INVISIBLE);
                        step=2;
                            break;
                    case 2:


                                if(generated_number.getText().toString().equals("123")) {
                                    steering=setup(steering);
                                    inform.setText("Liczba została poprawnie zapamiętana. Kliknij na przycisk by przejść do kolejnego etapu samouczka.");
                                    step=3;
                                    generated_number.setText("");
                                }
                                else
                                {
                                    steering=setup(steering);
                                     inform.setText("Niestety ciąg został źle podany. Dla przypomnienia, zapamiętywana liczba to ''123''. Proszę kliknąć w przycisk poniżej i ponownie wpisać liczbę.");
                                    step=1;
                                    generated_number.setText("");

                                }

                        break;
                    case 3:

                        inform.setText("Teraz nauczymy się korzystać z wprowadzania głosowego. Służy temu przycisk z wizerunkiem mikrofonu.");

                        step=4;
                        break;
                    case 4:
                        inform.setText("Jak widać, wygenerowany ciąg cyfr zwiększył się o jedną cyfrę. Tak będzie się działo  za każdym razem, gdy zostanie poprawnie zapamiętany poprzedni ciąg.");

                        generated_number.setText("5647");
                        step=5;
                        break;
                    case 5:
                        inform.setText("Przy każdej błędnej odpowiedzi zostanie utracona szansa. Liczbę szans można ustalić w opcjach. Proszę zapamiętać podany ciąg i wyrazić gotowość aby przejść dalej.");
                        step=6;
                        break;
                    case 6:
                        steering=setup(steering);
                        inform.setText("Proszę wprowadzić zapamiętaną liczbę, tym razem z użyciem wprowadzania głosowego. Jeśli to niemożliwe, prosze wprowadzić liczbę naciskając odpowiednie przyciski.");

                        step=7;
                        break;
                    case 7:


                        if(generated_number.getText().toString().equals("5647")) {
                            steering=setup(steering);
                            inform.setText("Liczba została poprawnie zapamiętana. Gratulacje, udało się pomyślnie zakończyć samouczek. Kliknij w przycisk poniżej aby przejsć do właściwej gry.");
                            step=8;
                            ok.setText("Przejdź do gry");
                            generated_number.setText("");
                        }
                        else
                        {
                            steering=setup(steering);

                            inform.setText("Niestety ciąg został źle podany. Dla przypomnienia, zapamiętywana liczba to ''5647''. Proszę kliknąć w przycisk poniżej i ponownie wpisać liczbę.");
                            step=6;
                            generated_number.setText("");

                        }
                        break;
                    case 8:
                        Intent i = new Intent(getBaseContext(), numbers.class);
                        startActivity(i);
                        finish();
                        break;


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
        private int setup(int steering)
        {
            Button ok = (Button) findViewById(R.id.ok);
            TextView generated_number = (TextView) findViewById(R.id.generated_number);
            if(steering==0)
            {
                ok.setText("Dalej");
                generated_number.setText("");

                ivisibility(false);
                steering=1;
                return steering;

            }

            else
            {
                ok.setText("Gotów");
                ivisibility(true);
                steering=0;
                return steering;
            }
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





    protected void ivisibility(boolean off)
    {

        if(off) {
            for (int i = 0; i<= 9; i++) {
                int id = getResources().getIdentifier("b" + i, "id", getPackageName());
                Button tmp = (Button) findViewById(id);
                tmp.setVisibility(View.INVISIBLE);

            }
            Button C = (Button)findViewById(R.id.bC);
            C.setVisibility(View.INVISIBLE);
            ImageButton Voice = (ImageButton)findViewById(R.id.bvoice);
            Voice.setVisibility(View.INVISIBLE);
            TextView generated_number = (TextView) findViewById(R.id.generated_number);
            generated_number.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            Button ok=(Button)findViewById(R.id.ok);
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_numbers);
            generated_number.setHeight(500);


            RelativeLayout.LayoutParams pok = (RelativeLayout.LayoutParams)ok.getLayoutParams();
            pok.leftMargin = 0;

            ok.setLayoutParams(pok);
            RelativeLayout.LayoutParams pgn = (RelativeLayout.LayoutParams)generated_number.getLayoutParams();
            pgn.bottomMargin = 300;

            generated_number.setLayoutParams(pgn);
        }
        else {
            for (int i = 0; i <= 9; i++) {
                int id = getResources().getIdentifier("b" + i, "id", getPackageName());
                Button tmp = (Button) findViewById(id);
                tmp.setVisibility(View.VISIBLE);


            }
            Button C = (Button)findViewById(R.id.bC);
            C.setVisibility(View.VISIBLE);
            ImageButton Voice = (ImageButton)findViewById(R.id.bvoice);
            Voice.setVisibility(View.VISIBLE);
            TextView generated_number = (TextView) findViewById(R.id.generated_number);
            generated_number.setGravity(Gravity.TOP);
            Button ok = (Button) findViewById(R.id.ok);
            RelativeLayout.LayoutParams pok = (RelativeLayout.LayoutParams)ok.getLayoutParams();
            RelativeLayout.LayoutParams pvc = (RelativeLayout.LayoutParams)Voice.getLayoutParams();
            pok.leftMargin = pvc.leftMargin;
            //p.topMargin = xxx; // in PX
            ok.setLayoutParams(pok);

            RelativeLayout.LayoutParams pgn = (RelativeLayout.LayoutParams)generated_number.getLayoutParams();
            pgn.bottomMargin = 600; // in PX
            //p.topMargin = xxx; // in PX
            generated_number.setLayoutParams(pgn);

        }
    }
    }

