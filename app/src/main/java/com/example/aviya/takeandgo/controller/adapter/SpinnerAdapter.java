package com.example.aviya.takeandgo.controller.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.aviya.takeandgo.R;

import java.util.List;

/**
 * Created by aviya on 10/12/2017.
 */

public class SpinnerAdapter extends BaseAdapter {
    private List<Integer> colors;
    private Activity activity;
    private LayoutInflater mLayoutInflater;

    public SpinnerAdapter(List<Integer> colors, Activity activity) {
        this.colors = colors;
        this.activity = activity;
        mLayoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return colors.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null){view = mLayoutInflater.inflate(R.layout.color_item,null);}
        TextView tv = (TextView)view.findViewById(R.id.colorTextView);
        tv.setText(colors.get(position)+"");
        return view;
    }
}
