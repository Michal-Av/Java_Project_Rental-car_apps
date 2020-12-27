package com.example.talia.android5778_5956_6419_01.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.talia.android5778_5956_6419_01.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonBranch = (Button)findViewById(R.id.buttonBranch);
        buttonBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(MainActivity.this, BranchActivity.class);
                startActivity(myIntent);
            }
        });
        Button buttonUser = (Button)findViewById(R.id.buttonUser);
        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(MainActivity.this, UserActivity.class);
                startActivity(myIntent);
            }
        });
        Button buttonCar = (Button)findViewById(R.id.buttonCar);
        buttonCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(MainActivity.this, CarActivity.class);
                startActivity(myIntent);
            }
        });
        Button buttonModel = (Button)findViewById(R.id.buttonModel);
        buttonModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(MainActivity.this, CarModelActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
