package com.example.talia.android5778_5956_6419_01.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.talia.android5778_5956_6419_01.R;
import com.example.talia.android5778_5956_6419_01.models.entities.User;

import java.util.List;

/**
 * Created by talia on 16/01/2018.
 */

public class UserAdapter extends BaseAdapter {
    List<User> list;
    LayoutInflater layoutInflater;

    private TextView userName;
    private TextView userPassword;

    private void findViews (View view)
    {
        userName=(TextView)view.findViewById(R.id.userName);
        userPassword=(TextView)view.findViewById(R.id.userPassword);
    }
    public UserAdapter (Context context, List<User> lst)
    {
        list= lst;
        layoutInflater = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount(){return list.size();}

    @Override
    public Object getItem (int position)
    {
        return list.get(position);
    }
    @Override
    public long getItemId (int position)
    {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = layoutInflater.inflate(R.layout.user_item, null);
        findViews(view);
        User user = list.get(position);
        userName.setText(user.getUserName());
        userPassword.setText(user.getUserPassword());
        return view;
    }
}
