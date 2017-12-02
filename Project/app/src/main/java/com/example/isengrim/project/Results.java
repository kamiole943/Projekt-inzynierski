package com.example.isengrim.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Results extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ListView lvr =(ListView) findViewById(R.id.lvr);
        List<String[]> list= ReadFromCSV();

        MultirowLVAdapter adapter=new MultirowLVAdapter(this, list);
        lvr.setAdapter(adapter);

    }


    public List ReadFromCSV() {
        String csv = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = "Number_Records.csv";
        String filePath = csv + File.separator + fileName;
        File file = new File(filePath);
        CSVReader reader;
        List<String[]> list = new ArrayList();
        try {
            if (file.exists() && !file.isDirectory()) {
                reader = new CSVReader(new FileReader(filePath), ';');
            } else {

                return list;
            }

            list=reader.readAll();
            reader.close();
        } catch (Exception e) {
            Log.i("", "Something went wrong");
            Log.e("", e.getMessage());
        }
        return list;
    }
}
