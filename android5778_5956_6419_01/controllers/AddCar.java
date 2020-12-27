package com.example.talia.android5778_5956_6419_01.controllers;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import com.example.talia.android5778_5956_6419_01.R;
import com.example.talia.android5778_5956_6419_01.models.backend.Const;
import com.example.talia.android5778_5956_6419_01.models.backend.Factory_Method;
import com.example.talia.android5778_5956_6419_01.models.datasources.MySQL_DBManager;
import com.example.talia.android5778_5956_6419_01.models.entities.Branch;
import com.example.talia.android5778_5956_6419_01.models.entities.Car;
import com.example.talia.android5778_5956_6419_01.models.entities.CarModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.talia.android5778_5956_6419_01.models.backend.Const.CarToContentValue;
import static com.example.talia.android5778_5956_6419_01.models.backend.Const.userToContentValue;

public class AddCar extends Activity implements View.OnClickListener{
    private Spinner spinnerBranchNumber;
    private EditText textCarNumber;
    private Spinner spinnerCarModel;
    private EditText textMileage;
    private Button buttonEnter;
    Car car = new Car();
    private List<CarModel> modelList;
    private List<Branch> branchList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        new AsyncTask<Void,Void,Void>() {
            @Override
            protected void onPostExecute(Void aVoid) {
                initSpinners();
            }

            @Override
            protected Void doInBackground(Void... params) {
                branchList = Factory_Method.getBackend().getBranch();
                modelList = Factory_Method.getBackend().getCarModels();
                return null;
            }
        }.execute();

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
        spinnerBranchNumber = (Spinner) findViewById(R.id.spinnerBranchNumber);
        textCarNumber = (EditText) findViewById(R.id.textCarNumber);
        spinnerCarModel = (Spinner) findViewById(R.id.spinnerCarModel);
        textMileage = (EditText) findViewById(R.id.textMileage);
        buttonEnter = (Button) findViewById(R.id.buttonEnter);
        buttonEnter.setOnClickListener(this);
    }

    /**
     * this function initialize the spinners
     */
    private void initSpinners() {
        List<Integer> models = new ArrayList<>();
        for (CarModel m:modelList) {
            models.add(m.getModelCode());
        }
        List<Integer> branches = new ArrayList<>();
        for (Branch b:branchList){
            branches.add(b.getBranchNumber());
        }

        ArrayAdapter<Integer> modelAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item,models);
        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCarModel.setAdapter(modelAdapter);
        ArrayAdapter<Integer> branchAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item,branches);
        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBranchNumber.setAdapter(branchAdapter);

        spinnerCarModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                car.setCarModel(Integer.valueOf((spinnerCarModel.getItemAtPosition(position).toString())));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                car.setCarModel(Integer.valueOf((spinnerCarModel.getItemAtPosition(0).toString())));
            }
        });
        spinnerBranchNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                car.setNumBranch(Integer.valueOf(spinnerBranchNumber.getItemAtPosition(position).toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                car.setNumBranch(Integer.valueOf(spinnerBranchNumber.getItemAtPosition(0).toString()));
            }
        });
    }

    public void setbuttonEnter(){
        //car.setNumBranch(this.spinnerBranchNumber..getText().toString());
        car.setNumCar(Integer.valueOf(this.textCarNumber.getText().toString()));
        //car.setCarModel(this.
        car.setMileage(Integer.valueOf(this.textMileage.getText().toString()));
        final ContentValues c = CarToContentValue(car);

        new AsyncTask<Void, Void, Boolean>() {//all the act which conected with the backend will commited in AsyncTask
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                if (result)
                {
                    Intent intent = new Intent(AddCar.this, MainActivity.class);
                    intent.putExtra(Const.CarConst.NUM_BRANCH, car.getNumBranch());
                    intent.putExtra(Const.CarConst.NUM_CAR, car.getNumCar());
                    intent.putExtra(Const.CarConst.CAR_MODEL, car.getCarModel());
                    intent.putExtra(Const.CarConst.MILEAGE, car.getMileage());
                    Toast.makeText(getBaseContext(), "רכב חדש נוסף", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try{
                Factory_Method.getBackend().addCar(c);
                    return true;
                }
                catch (final Exception e) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {Toast.makeText(AddCar.this, e.getMessage(), Toast.LENGTH_LONG).show();}});
                    return false;
                }
            }
        }.execute();
    }
}



