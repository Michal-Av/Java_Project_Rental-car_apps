package com.example.talia.android5778_5956_6419_02.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.talia.android5778_5956_6419_02.R;
import android.support.v4.app.Fragment;

/**
 * Created by michalus.av on 27/02/2018.
 */

public class FragmentWelcome extends android.support.v4.app.Fragment {
    public FragmentWelcome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

}
