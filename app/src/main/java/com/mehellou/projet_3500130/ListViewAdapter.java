package com.mehellou.projet_3500130;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lotfi on 22/01/18.
 */

public class ListViewAdapter extends BaseAdapter {
    public ArrayList<HashMap<String,String>> list;
    Activity activity;
    TextView txtFirst;
    TextView txtSecond;
    TextView txtThird;


    public ListViewAdapter(Activity activity, ArrayList<HashMap<String,String>> l){
        super();
        this.activity = activity;
        this.list = l;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        if(view == null){
            view = inflater.inflate(R.layout.column_row,null);
            txtFirst=(TextView)view.findViewById(R.id.score);
            txtSecond=(TextView)view.findViewById(R.id.level);
            txtThird=(TextView)view.findViewById(R.id.date);
        }
        HashMap<String,String> map = list.get(i);
        txtFirst.setText(map.get(ConstantStat.FIRST_COLUMN));
        txtSecond.setText(map.get(ConstantStat.SECOND_COLUMN));
        txtThird.setText(map.get(ConstantStat.THIRD_COLUMN));

        return view;
    }

}
