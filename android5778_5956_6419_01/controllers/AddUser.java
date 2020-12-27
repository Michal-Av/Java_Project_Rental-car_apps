package com.example.talia.android5778_5956_6419_01.controllers;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.talia.android5778_5956_6419_01.R;
import com.example.talia.android5778_5956_6419_01.models.backend.Const;
import com.example.talia.android5778_5956_6419_01.models.backend.Factory_Method;
import com.example.talia.android5778_5956_6419_01.models.entities.User;

import static com.example.talia.android5778_5956_6419_01.models.backend.Const.userToContentValue;

public class AddUser extends Activity implements View.OnClickListener {

    private EditText textUserName;
    private EditText textUserPassword;
    private Button buttonEnter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
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
        textUserName = (EditText) findViewById(R.id.textUserName);
        textUserPassword = (EditText) findViewById(R.id.textUserPassword);
        buttonEnter = (Button) findViewById(R.id.buttonEnter);
        buttonEnter.setOnClickListener(this);
    }

    public void setbuttonEnter(){
        final User user = new User();
        user.setUserName(this.textUserName.getText().toString());
        user.setUserPassword(this.textUserPassword.getText().toString());
        final ContentValues c = userToContentValue(user);
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
       try{
        Factory_Method.getBackend().addUser(c);
         return true;
        }
       catch (final Exception e) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {Toast.makeText(AddUser.this, e.getMessage(), Toast.LENGTH_LONG).show();}});
                return false;
            }
        }
    }.execute();
    }
}
