package com.example.talia.android5778_5956_6419_02.controllers;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.talia.android5778_5956_6419_02.R;
import com.example.talia.android5778_5956_6419_02.models.backend.Factory_Method;
import com.example.talia.android5778_5956_6419_02.models.entities.Branch;
import com.example.talia.android5778_5956_6419_02.models.entities.Car;
import com.example.talia.android5778_5956_6419_02.models.entities.Order;

import java.util.List;

import static java.security.AccessController.getContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBranches extends Fragment {

    View view;
    SearchView branchSearchView;
    ExpandableListView expandableList;
    AdapterBranches adapterExList;
    static OnCarOrderedListener activityListener;

    public interface OnCarOrderedListener{
        void newReservationForClient(Order reservation);
    }
    public FragmentBranches() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_branches, container, false);
        expandableList=(ExpandableListView)view.findViewById(R.id.branchesExpandableList);
        Bundle args = getArguments();
        final int id = args.getInt("ID");
        if (id == 0)
            Toast.makeText(getContext(), "no ID was found", Toast.LENGTH_SHORT);

        new AsyncTask<Object, Object, List<Branch>>() {

            @Override
            protected void onPostExecute(List<Branch> result) {
                super.onPostExecute(result);
                if (result !=null)//message for the user
                    adapterExList= new AdapterBranches(getContext(),result,id);
                    expandableList.setAdapter(adapterExList);
            }

            @Override
            protected List<Branch> doInBackground(Object... params) {
                return Factory_Method.getBackend().getBranches();
            }

        }.execute();



        //filter view
        branchSearchView = (SearchView)view.findViewById(R.id.branchSearchView);
        //if filter data changed
        branchSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterExList.getFilter().filter(query);
                adapterExList.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterExList.getFilter().filter(newText);
                adapterExList.notifyDataSetChanged();
                return false;
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            activityListener = (OnCarOrderedListener)activity;
        }
        catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    public void error(final Exception e)
    {
        getActivity().runOnUiThread(new Runnable() {
        @Override
        public void run() {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    });
    }
}