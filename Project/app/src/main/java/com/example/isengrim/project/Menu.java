package com.example.isengrim.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final Button Gra = (Button) findViewById(R.id.Gra);
        Gra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), numbers.class);
                startActivity(i);
            }

        });

        final Button Samouczek = (Button) findViewById(R.id.Samouczek);
        Samouczek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Tutorial.class);
                startActivity(i);
            }

        });

        final Button Opcje = (Button) findViewById(R.id.Opcje);
        Opcje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Options.class);
                startActivity(i);
            }

        });

    }
}
