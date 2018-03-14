package com.example.aviya.takeandgo.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.aviya.takeandgo.R;
import com.example.aviya.takeandgo.model.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aviya on 07/12/2017.
 */

public class MyUserAdapter extends BaseAdapter {

    private Context context;
    private List<User> list = new ArrayList<>();
    private LayoutInflater mLayoutInflater;

    public MyUserAdapter(Context cont, List<User> list) {
        this.context = cont;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() { return list.size();}

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = list.get(position);
        View view = mLayoutInflater.inflate(R.layout.user_item,null);
        TextView userName = (TextView) view.findViewById(R.id.user_present_name);
        TextView userPassword = (TextView) view.findViewById(R.id.user_present_password);
        userName.setText(user.getUserName());
        String present_password = "****";
        String real_password = user.getPassword();
        for (int i=4; i<real_password.length();i++)
            present_password += real_password.charAt(i);
        userPassword.setText(present_password);
        return view;
    }
}
