package com.example.talia.android5778_5956_6419_01.controllers;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.talia.android5778_5956_6419_01.R;
import com.example.talia.android5778_5956_6419_01.models.backend.Factory_Method;
import com.example.talia.android5778_5956_6419_01.models.entities.User;

import java.util.ArrayList;
import java.util.List;

import static com.example.talia.android5778_5956_6419_01.R.id.buttonUser;

public class UserActivity extends Activity implements View.OnClickListener {

    ListView users;
    List<User> list = new ArrayList<>();
    private Button ButtonAddUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        users= (ListView)findViewById(R.id.users);
        findViews();
        new AsyncTask<Void, Void, Void>()
        {
            @Override
            protected void onPostExecute(Void v)
            {
                users.setAdapter(new UserAdapter(getBaseContext(), list));
            }
            @Override
            protected Void doInBackground(Void... params){
                list= Factory_Method.getBackend().getUser();
                return null;
            }
        }.execute();
    }
    private void findViews() {
        ButtonAddUser = (Button)findViewById(R.id.ButtonAddUser);
        ButtonAddUser.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == ButtonAddUser)
            try {
                setButtonAddUser();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    public void setButtonAddUser()
    {
        Intent intent = new Intent(UserActivity.this, AddUser.class);
        startActivity(intent);
        return;
    }
}
