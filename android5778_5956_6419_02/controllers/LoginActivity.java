package com.example.talia.android5778_5956_6419_02.controllers;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.talia.android5778_5956_6419_02.R;
import com.example.talia.android5778_5956_6419_02.models.backend.Const;
import com.example.talia.android5778_5956_6419_02.models.backend.Factory_Method;
import com.example.talia.android5778_5956_6419_02.models.datasource.MySQL_DBManager;
import com.example.talia.android5778_5956_6419_02.models.entities.Customer;
import com.example.talia.android5778_5956_6419_02.models.entities.User;

import java.util.List;

import static com.example.talia.android5778_5956_6419_02.models.backend.Const.userToContentValue;

public class LoginActivity extends Activity implements View.OnClickListener {

    private boolean flag;
    private EditText textUserName;
    private EditText textPassword;
    private Button buttonEnter;
    private TextView goToRegisterView;
    private CheckBox savePreferences;
    private MySQL_DBManager manager;

    private int idToPass;       // client id to pass to be menu activity so it "knows" which client is signed in
    private Boolean textChange; // a bool parameter to check if the login info changed and needs to be reevaluated

    private void findViews() {
        textUserName = (EditText) findViewById(R.id.textUserName);
        textPassword = (EditText) findViewById(R.id.textPassword);
        goToRegisterView = (TextView) findViewById(R.id.goToRegisterView);
        buttonEnter = (Button) findViewById(R.id.buttonEnter);
        savePreferences = (CheckBox) findViewById(R.id.savePreferencesCheckBox);


        goToRegisterView.setOnClickListener(this);
        buttonEnter.setOnClickListener(this);

        //if login info changed set the textChange value to true
        textUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textChange = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        textPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textChange = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v == buttonEnter) {
            Login();
        } else if (v == goToRegisterView) {
            Register();
        }
    }

    /**
     * moving to the registration activity if there is no existing user/client
     */
    private void Register() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * validating the input for login info and
     * moving to menu activity if validated
     */
    private void Login() {
        if (textChange) {
            if (setbuttonEnter()) {
                if (savePreferences.isChecked())
                    savePreferencesData();
                goToMenu();
            }
           // else  Toast.makeText(getBaseContext(), "שם משתמש או סיסמא לא נכונים", Toast.LENGTH_LONG).show();

        } else {
            if (savePreferences.isChecked())
                savePreferencesData();
            goToMenu();
        }
    }

    public boolean setbuttonEnter() {
        final String firstName = this.textUserName.getText().toString();
        final String password = this.textPassword.getText().toString();

        new AsyncTask<Void, Void, List<Customer>>() {
            @Override
            protected List<Customer> doInBackground(Void... params) {
                final List<Customer> customerList = Factory_Method.getBackend().getCustomer();
                return customerList;
            }

            @Override
            protected void onPostExecute(List<Customer> customerList) {
                // flag = manager.existCustomer(firstName,password);
                for (Customer customer : customerList)
                    if ((customer.getFirstName()).equals(firstName) && (customer.getPassword()).equals(password)) {
                        idToPass = customer.getId();
                        flag = true;
                    }
                if(!flag) {
                    Toast.makeText(getBaseContext(), "שם משתמש או סיסמא לא נכונים", Toast.LENGTH_LONG).show();
                }
                  //else{
                    //    Toast.makeText(getBaseContext(), "שם משתמש או סיסמא לא נכונים", Toast.LENGTH_LONG).show();
                     //   flag = false;
                   // }

            }
        }.execute();
    return flag;
    }
        /*new AsyncTask<Void, Void, Boolean>() {//all the act which conected with the backend will commited in AsyncTask
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                if (result)
                {
                    flag=result;
                    idToPass = manager.findIdByUserName(userName).get_id();
                    //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                     //startActivity(intent);
                }
                else{
                    flag=result;
                    Toast.makeText(getBaseContext(), "שם משתמש או סיסמא לא נכונים", Toast.LENGTH_LONG).show();
            }
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                return Factory_Method.getBackend().existCustomer(firstName,password);
            }
        }.execute();
        return flag;
        */


    private void goToMenu() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("ID",idToPass);
        startActivity(intent);
    }

    /**
     * save the login info in the SharedPreferences for future use
     */
    private void savePreferencesData() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        String userName = textUserName.getText().toString();
        String password = textPassword.getText().toString();

        editor.putString("USER_NAME",userName);
        editor.putString("PASSWORD",password);
        if(idToPass == 0) { // no id means no client found for this userClient info
            Toast.makeText(this, "no ID for this user was found", Toast.LENGTH_LONG).show();
            return;
        }
        editor.putInt("ID",idToPass);
        editor.commit();
    }

    /**
     * check if there is an info in the SharedPreferences, if there is load it to the proper view
     * and to the id to pass.
     */
    private void addPreference() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(preferences.contains("USER_NAME")) {
            textUserName.setText(preferences.getString("USER_NAME", null));
            savePreferences.setChecked(true);
        }
        if(preferences.contains("PASSWORD"))
            textPassword.setText(preferences.getString("PASSWORD",null));
        if(preferences.contains("ID"))
            idToPass = preferences.getInt("ID",0);
        textChange = false; //set textChange false because so far there was no change in login info
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Take & Go");
        manager = Factory_Method.getBackend();
        findViews();
        addPreference();
        buttonEnter.setVisibility(View.INVISIBLE);
        new AsyncTask<Void,Boolean,Boolean>(){
            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if(aBoolean){
                    //login button is not visible so we dont press it before the list database is initialized
                    buttonEnter.setVisibility(View.VISIBLE);
                }
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try{
                    //initializing all the lists from database must be done here because its the launcher activity
                    manager.initializeLists();
                    return true;
                }
                catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        }.execute();
    }
}