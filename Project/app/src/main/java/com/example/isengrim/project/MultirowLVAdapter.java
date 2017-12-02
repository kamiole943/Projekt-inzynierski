package com.example.isengrim.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MultirowLVAdapter extends BaseAdapter{

    //public ArrayList<HashMap<String, String>> list;
    public List<String[]> list;
    Activity activity;
    TextView txtFirst;
    TextView txtSecond;
    TextView txtThird;

    public MultirowLVAdapter(Activity activity,List<String[]> list){
        super();
        this.activity=activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub



        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.list_row, null);

            txtFirst=(TextView) convertView.findViewById(R.id.date);
            txtSecond=(TextView) convertView.findViewById(R.id.length);
            txtThird=(TextView) convertView.findViewById(R.id.time);


        }

        String[] map=list.get(position);
        txtFirst.setText(map[0]);
        txtSecond.setText(map[1]);
        txtThird.setText(map[2]);
        return convertView;
    }

}