package com.example.talia.android5778_5956_6419_02.controllers;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.talia.android5778_5956_6419_02.R;
import com.example.talia.android5778_5956_6419_02.models.backend.Factory_Method;
import com.example.talia.android5778_5956_6419_02.models.entities.Car;

import java.util.List;

public class FragmentFreeCars extends Fragment {
    View view;
    ExpandableListView expandList;
    AdapterCar adapterExList;

    public FragmentFreeCars() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_free_cars, container, false);
        expandList=(ExpandableListView) view.findViewById(R.id.expandble_list);

        new AsyncTask<Object, Object, List<Car>>() {//all the act which conected with the backend will commited in AsyncTask


            @Override
            protected void onPostExecute(List<Car> result) {
                super.onPostExecute(result);
                if (result !=null)//message for the user
                    adapterExList= new AdapterCar(getContext(),result);
                expandList.setAdapter(adapterExList);
            }

            @Override
            protected List<Car> doInBackground(Object... params) {
                return Factory_Method.getBackend().allFreeCars();
            }

        }.execute();

        return view;
    }

}
