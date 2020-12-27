package com.example.talia.android5778_5956_6419_01.controllers;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.talia.android5778_5956_6419_01.R;
import com.example.talia.android5778_5956_6419_01.models.backend.Factory_Method;
import com.example.talia.android5778_5956_6419_01.models.entities.Branch;

import java.util.ArrayList;
import java.util.List;

public class BranchActivity extends Activity {

    ListView branchListView;
    TextView branchWait;
    List<Branch> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
        branchListView= (ListView)findViewById(R.id.branchListView);
        branchWait= (TextView)findViewById(R.id.branchWait);
        new AsyncTask<Void, Void, Void>()
        {
            @Override
            protected void onPostExecute(Void v)
            {
                branchWait.setVisibility(View.GONE);
                branchListView.setAdapter(new BranchAdapter(getBaseContext(), list));
            }
            @Override
            protected Void doInBackground(Void... params){
                list= Factory_Method.getBackend().getBranch();
                return null;
            }
        }.execute();
    }
}
