package com.example.talia.android5778_5956_6419_01.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.talia.android5778_5956_6419_01.R;
import com.example.talia.android5778_5956_6419_01.models.entities.CarModel;

import java.util.List;

/**
 * Created by michalus.av on 20/01/2018.
 */

public class CarModelAdapter extends BaseAdapter {
        List<CarModel> list;
        LayoutInflater layoutInflater;

        private TextView modelCode;
        private TextView companyName;
        private TextView modelName;
        private TextView engineCapacity;
        private TextView gearbox;
        private TextView seats;

        private void findViews (View view)
        {
            modelCode=(TextView)view.findViewById(R.id.modelCode);
            companyName=(TextView)view.findViewById(R.id.companyName);
            modelName=(TextView)view.findViewById(R.id.modelName);
            engineCapacity=(TextView)view.findViewById(R.id.engineCapacity);
            gearbox=(TextView)view.findViewById(R.id.gearbox);
            seats=(TextView)view.findViewById(R.id.seats);
        }
        public CarModelAdapter (Context context, List<CarModel> lst)
        {
            list= lst;
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            View view = layoutInflater.inflate(R.layout.car_model_item, null);
            findViews(view);
            CarModel carModel = list.get(position);
            modelCode.setText(String.valueOf(carModel.getModelCode()));
            companyName.setText(carModel.getCompanyName());
            modelName.setText(carModel.getModelName());
            engineCapacity.setText(String.valueOf(carModel.getEngineCapacity()));
            gearbox.setText(carModel.getGearbox().toString());
            seats.setText(String.valueOf(carModel.getSeats()));
            return view;
        }
    }

