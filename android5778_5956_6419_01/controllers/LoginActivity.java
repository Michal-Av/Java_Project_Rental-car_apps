package com.example.talia.android5778_5956_6419_01.controllers;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.ContentValues;
import android.widget.TextView;
import android.widget.Toast;

import com.example.talia.android5778_5956_6419_01.R;
import com.example.talia.android5778_5956_6419_01.models.backend.Const;
import com.example.talia.android5778_5956_6419_01.models.backend.Factory_Method;
import com.example.talia.android5778_5956_6419_01.models.entities.User;


import javax.sql.RowSetReader;
import com.example.talia.android5778_5956_6419_01.models.datasources.MySQL_DBManager;

import static com.example.talia.android5778_5956_6419_01.models.backend.Const.userToContentValue;

public class LoginActivity extends Activity implements View.OnClickListener {

    private TextView textEnterSystem;
    private EditText textUserName;
    private EditText textPassword;
    private Button buttonEnter;
    private Button buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        if (v == buttonClear)
            setbuttonClear();
    }

    private void findViews() {
        textEnterSystem = (TextView) findViewById(R.id.textEnterSystem);
        textUserName = (EditText) findViewById(R.id.textUserName);
        textPassword = (EditText) findViewById(R.id.textPassword);
        buttonEnter = (Button) findViewById(R.id.buttonEnter);
        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonEnter.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
    }

    public void setbuttonEnter() throws Exception {
        final User user = new User();
        user.setUserName(this.textUserName.getText().toString());
        user.setUserPassword(this.textPassword.getText().toString());
        final ContentValues c = userToContentValue(user);

        new AsyncTask<Void, Void, Boolean>() {//all the act which conected with the backend will commited in AsyncTask
        protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                if (result)
                {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra(Const.UserConst.USER_NAME, user.getUserName());
                    intent.putExtra(Const.UserConst.USER_PASSWORD, user.getUserPassword());
                    startActivity(intent);
                }
                else
                    Toast.makeText(getBaseContext(), "שם משתמש או סיסמא לא נכונים", Toast.LENGTH_LONG).show();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
              return Factory_Method.getBackend().userExist(c);
            }
        }.execute();
    }

    public void setbuttonClear() {
        textPassword.setText("");
        textUserName.setText("");

    }
}
