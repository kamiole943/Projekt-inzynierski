package com.example.isengrim.project;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Options extends AppCompatActivity {

    int chances=1;
    int length=2;
    int voice=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Check_if_config_exists();
        Get_Config();
        Configure_Layout(length, chances, voice);


        final TextView tbsl = (TextView)findViewById(R.id.tbsl);

        SeekBar sbsl = (SeekBar)findViewById(R.id.sbsl);
        tbsl.setText(String.valueOf(sbsl.getProgress()+1));

        sbsl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar sbsl, int progress,boolean fromUser) {
                tbsl.setText(String.valueOf(progress+1));
                length=progress+1;
                Save_Config_To_File(length,chances,voice);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void click_chance(View view) {

        switch (view.getId()) {
            case R.id.bc1:
                chances=1;
                break;
            case R.id.bc2:
                chances=2;
                break;
            case R.id.bc3:
                chances=3;
                break;


        }
        Save_Config_To_File(length,chances,voice);
        Configure_Layout(length, chances, voice);

    }
    public void click_voice(View view) {

        switch (view.getId()) {
            case R.id.brp:
                voice=1;
                break;
            case R.id.bat:
                voice=2;

        }
        Save_Config_To_File(length,chances,voice);
        Configure_Layout(length, chances, voice);
    }

    public  void Check_if_config_exists() {
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
    }
    public void Get_Config()
    {
        String filename = "Number_Config.txt";
        String filepath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + filename;
        File file = new File(filepath);

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

                Log.e("Exception",   setup.toString());
                String[] separated = setup.toString().split("\\|");
                Log.e("Exception",  separated[0]);
                Log.e("Exception",  separated[1]);
                Log.e("Exception",  separated[2]);
                length=Integer.parseInt(separated[0]);
                chances=Integer.parseInt(separated[1]);
                voice=Integer.parseInt(separated[2]);


            }
            catch (IOException e) {
                Log.e("Exception", "File read failed: " + e.toString());
            }


    }
    public void Save_Config_To_File(int length, int chances, int voice)
    {
        String filename = "Number_Config.txt";
        String filepath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + filename;
        File file = new File(filepath);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(length+"|");
            writer.write(chances+"|");
            writer.write(voice+"|");
            writer.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

        public void Configure_Layout(int length, int chances, int voice)
        {
            Button bc1 = (Button)findViewById(R.id.bc1);
            Button bc2 = (Button)findViewById(R.id.bc2);
            Button bc3 = (Button)findViewById(R.id.bc3);
            Button brp = (Button)findViewById(R.id.brp);
            Button bat = (Button)findViewById(R.id.bat);
            TextView tbsl = (TextView)findViewById(R.id.tbsl);
            SeekBar sbsl = (SeekBar)findViewById(R.id.sbsl);

                tbsl.setText(String.valueOf(length));
                sbsl.setProgress(length-1);
            switch(chances)
            {
                case(1):
                    bc1.setBackgroundResource(R.color.colorPrimary);

                    bc2.setBackgroundResource(android.R.drawable.btn_default);
                    bc3.setBackgroundResource(android.R.drawable.btn_default);
                    // bc1.setBackgroundColor(0x993F6C);
                    //bc2.setBackgroundColor(Color.TRANSPARENT);
                    //bc3.setBackgroundColor(Color.TRANSPARENT);
                    break;
                case(2):
                    bc2.setBackgroundResource(R.color.colorPrimary);

                    bc1.setBackgroundResource(android.R.drawable.btn_default);
                    bc3.setBackgroundResource(android.R.drawable.btn_default);
                   // bc1.setBackgroundColor(Color.TRANSPARENT);
                    //bc2.setBackgroundColor(0x993F6C);
                    //bc3.setBackgroundColor(Color.TRANSPARENT);
                    break;
                case(3):
                    bc3.setBackgroundResource(R.color.colorPrimary);

                    bc1.setBackgroundResource(android.R.drawable.btn_default);
                    bc2.setBackgroundResource(android.R.drawable.btn_default);
                   // bc1.setBackgroundColor(Color.TRANSPARENT);
                    //bc2.setBackgroundColor(Color.TRANSPARENT);
                    //bc3.setBackgroundColor(0x993F6C);
                    break;
            }
            switch(voice)
            {
                case(1):
                    brp.setBackgroundResource(R.color.colorPrimary);
                    bat.setBackgroundResource(android.R.drawable.btn_default);
                    break;
                case(2):
                    bat.setBackgroundResource(R.color.colorPrimary);
                    brp.setBackgroundResource(android.R.drawable.btn_default);
                    break;
            }


        }
}

