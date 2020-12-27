package com.example.talia.android5778_5956_6419_01.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.talia.android5778_5956_6419_01.R;
import com.example.talia.android5778_5956_6419_01.models.backend.Factory_Method;
import com.example.talia.android5778_5956_6419_01.models.entities.Car;

import java.util.ArrayList;
import java.util.List;

public class CarActivity extends Activity implements View.OnClickListener {

    ListView cars;
    List<Car> list = new ArrayList<>();
    private Button ButtonAddCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        cars= (ListView)findViewById(R.id.Cars);
        findViews();
        new AsyncTask<Void, Void, Void>()
        {
            @Override
            protected void onPostExecute(Void v)
            {
                cars.setAdapter(new CarAdapter(getBaseContext(), list));
            }
            @Override
            protected Void doInBackground(Void... params){
                list= Factory_Method.getBackend().getCar();
                return null;
            }
        }.execute();
    }
    private void findViews() {
        ButtonAddCar = (Button) findViewById(R.id.addCar);
        ButtonAddCar.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == ButtonAddCar)
            try {
                setButtonAddCar();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    public void setButtonAddCar()
    {
        Intent intent = new Intent(CarActivity.this, AddCar.class);
        startActivity(intent);
        return;
    }
}

