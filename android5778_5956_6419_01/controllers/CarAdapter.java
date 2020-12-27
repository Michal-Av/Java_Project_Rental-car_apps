package com.example.talia.android5778_5956_6419_01.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.talia.android5778_5956_6419_01.R;
import com.example.talia.android5778_5956_6419_01.models.entities.Car;

import java.util.List;

/**
 * Created by michalus.av on 19/01/2018.
 */

public class CarAdapter extends BaseAdapter {
    List<Car> list;
    LayoutInflater layoutInflater;
    private TextView numBranch;
    private TextView carModel;
    private TextView mileage;
    private TextView numCar;

    private void findViews (View view)
    {
        numBranch=(TextView)view.findViewById(R.id.branchNumber);
        carModel=(TextView)view.findViewById(R.id.carModel);
        mileage=(TextView)view.findViewById(R.id.mileage);
        numCar=(TextView)view.findViewById(R.id.carNumber);
    }
    public CarAdapter (Context context, List<Car> lst)
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
        View view = layoutInflater.inflate(R.layout.car_item, null);
        findViews(view);
        Car car = list.get(position);
        numBranch.setText(String.valueOf(car.getNumBranch()));
        carModel.setText(String.valueOf(car.getCarModel()));
        mileage.setText(String.valueOf(car.getMileage()));
        numCar.setText(String.valueOf(car.getNumCar()));
        return view;
    }
}
