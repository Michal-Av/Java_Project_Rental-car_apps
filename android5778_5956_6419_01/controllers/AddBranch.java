/*
package com.example.talia.android5778_5956_6419_01.controllers;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.talia.android5778_5956_6419_01.R;
import com.example.talia.android5778_5956_6419_01.models.backend.Const;
import com.example.talia.android5778_5956_6419_01.models.backend.Factory_Method;
import com.example.talia.android5778_5956_6419_01.models.entities.Branch;
import com.example.talia.android5778_5956_6419_01.models.entities.User;

import static com.example.talia.android5778_5956_6419_01.models.backend.Const.userToContentValue;

public class AddBranch extends Activity {

    private EditText textBranchNumber;
    private EditText textAddress;
    private EditText textNumParkingSpace;
    private Button buttonEnter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_branch);
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
        textBranchNumber = (EditText) findViewById(R.id.textBranchNumber);
        textAddress = (EditText) findViewById(R.id.textAddress);
        textNumParkingSpace = (EditText) findViewById(R.id.textNumParkingSpace);
        buttonEnter = (Button) findViewById(R.id.buttonEnter);
        buttonEnter.setOnClickListener(this);
    }

    public void setbuttonEnter(){
        final Branch branch = new Branch();
        branch.setBranchNumber(Integer.valueOf(this.textBranchNumber.getText().toString()));
        branch.setAddress(this.textAddress.getText().toString());
        branch.setNumParkingSpaces(Integer.valueOf(this.textNumParkingSpace.getText().toString()));
        final ContentValues c = userToContentValue(branch);
        new AsyncTask<Void, Void, Boolean>() {//all the act which conected with the backend will commited in AsyncTask
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                if (result)
                {
                    Intent intent = new Intent(AddUser.this, MainActivity.class);
                    intent.putExtra(Const.UserConst.USER_NAME, user.getUserName());
                    intent.putExtra(Const.UserConst.USER_PASSWORD, user.getUserPassword());
                    Toast.makeText(getBaseContext(), "משתמש חדש נוסף", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
                else
                    Toast.makeText(getBaseContext(), "משתמש קיים במערכת", Toast.LENGTH_LONG).show();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                return Factory_Method.getBackend().addUser(c);
            }
        }.execute();

    }
}
*/