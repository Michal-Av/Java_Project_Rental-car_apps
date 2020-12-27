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
import com.example.talia.android5778_5956_6419_01.models.entities.CarModel;

import java.util.ArrayList;
import java.util.List;

public class CarModelActivity extends Activity implements View.OnClickListener {

    ListView carModels;
    List<CarModel> list;
    private Button ButtonAddCarModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_model);
        carModels= (ListView)findViewById(R.id.carModels);
        findViews();
        new AsyncTask<Void, Void, Void>()
        {
            @Override
            protected void onPostExecute(Void v)
            {
                carModels.setAdapter(new CarModelAdapter(getBaseContext(), list));
            }
            @Override
            protected Void doInBackground(Void... params){
                list= Factory_Method.getBackend().getCarModels();
                return null;
            }
        }.execute();
    }
    private void findViews() {
        ButtonAddCarModel = (Button)findViewById(R.id.ButtonAddCarModel);
        ButtonAddCarModel.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == ButtonAddCarModel)
            try {
                setButtonAddCarModel();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    public void setButtonAddCarModel()
    {
        Intent intent = new Intent(CarModelActivity.this, AddCarModel.class);
        startActivity(intent);
        return;
    }
}
