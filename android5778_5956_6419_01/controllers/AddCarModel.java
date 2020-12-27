package com.example.talia.android5778_5956_6419_01.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.talia.android5778_5956_6419_01.R;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import com.example.talia.android5778_5956_6419_01.R;
import com.example.talia.android5778_5956_6419_01.models.backend.Const;
import com.example.talia.android5778_5956_6419_01.models.backend.Factory_Method;
import com.example.talia.android5778_5956_6419_01.models.entities.Car;
import com.example.talia.android5778_5956_6419_01.models.entities.CarModel;
import com.example.talia.android5778_5956_6419_01.models.entities.Gearbox;

import static com.example.talia.android5778_5956_6419_01.models.backend.Const.CarModelToContentValue;
import static com.example.talia.android5778_5956_6419_01.models.backend.Const.CarToContentValue;
import static com.example.talia.android5778_5956_6419_01.models.backend.Const.userToContentValue;

public class AddCarModel extends Activity implements View.OnClickListener{
    private EditText textModelCode;
    private EditText textCompanyName;
    private EditText textModelName;
    private EditText textEngineCapacity;
    private Spinner spinnerGearbox;
    private EditText textSeats;
    private Button buttonEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car_model);
        findViews();
    }
    @Override
    public void onClick(View v) {
        if (v == buttonEnter)
            try {
                setbuttonEnter();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    private void findViews() {
        textModelCode = (EditText) findViewById(R.id.textModelCode);
        textCompanyName= (EditText) findViewById(R.id.textCompanyName);
        textModelName = (EditText) findViewById(R.id.textModelName);
        textEngineCapacity = (EditText) findViewById(R.id.textEngineCapacity);
        spinnerGearbox = (Spinner) findViewById(R.id.spinnerGearbox);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gearbox, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGearbox.setAdapter(adapter);
        textSeats = (EditText) findViewById(R.id.textSeats);
        buttonEnter = (Button) findViewById(R.id.buttonEnter);
        buttonEnter.setOnClickListener(this);
    }

    public void setbuttonEnter(){
        final CarModel carModel = new CarModel();
        carModel.setModelCode(Integer.valueOf(this.textModelCode.getText().toString()));
        carModel.setCompanyName(this.textCompanyName.getText().toString());
        carModel.setModelName(this.textModelName.getText().toString());
        carModel.setEngineCapacity(Double.valueOf(this.textEngineCapacity.getText().toString()));
        carModel.setGearbox(Gearbox.valueOf(this.spinnerGearbox.getSelectedItem().toString()));
        carModel.setSeats(Integer.valueOf(this.textSeats.getText().toString()));
        final ContentValues c = CarModelToContentValue(carModel);
        new AsyncTask<Void, Void, Boolean>() {//all the act which conected with the backend will commited in AsyncTask
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                if (result)
                {
                    Intent intent = new Intent(AddCarModel.this, MainActivity.class);
                    intent.putExtra(Const.CarModelConst.MODEL_CODE, carModel.getModelCode());
                    intent.putExtra(Const.CarModelConst.COMPANY_NAME, carModel.getCompanyName());
                    intent.putExtra(Const.CarModelConst.MODEL_NAME, carModel.getModelName());
                    intent.putExtra(Const.CarModelConst.ENGINE_CAPACITY, carModel.getEngineCapacity());
                    intent.putExtra(Const.CarModelConst.GEARBOX, carModel.getGearbox());
                    intent.putExtra(Const.CarModelConst.SEATS, carModel.getSeats());
                    Toast.makeText(getBaseContext(), "דגם חדש נוסף", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                Factory_Method.getBackend().addCarModel(c);
                    return true;
                }
                catch (final Exception e) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {Toast.makeText(AddCarModel.this, e.getMessage(), Toast.LENGTH_LONG).show();}});
                    return false;
                }
            }
        }.execute();
    }}



