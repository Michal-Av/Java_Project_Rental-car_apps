package com.example.talia.android5778_5956_6419_02.controllers;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.talia.android5778_5956_6419_02.R;
import com.example.talia.android5778_5956_6419_02.models.backend.Factory_Method;
import com.example.talia.android5778_5956_6419_02.models.entities.Branch;
import com.example.talia.android5778_5956_6419_02.models.entities.Car;

import java.util.List;

/**
 * Created by michalus.av on 23/02/2018.
 */

public class AdapterCar extends BaseExpandableListAdapter {
    Context context;
    List<Car> carList;
    LayoutInflater layoutInflater;

    public AdapterCar(Context context, List<Car> carList) {
        this.context = context;
        this.carList = carList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return carList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.expnd_list_car_header,parent,false);
        Car car = carList.get(groupPosition);
        TextView textView = (TextView)view.findViewById(R.id.carNumberHeader);
        textView.setText( String.valueOf(car.getNumCar()));
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.child_car_item,parent,false);
        TextView textView = (TextView)view.findViewById(R.id.child_item1);
        final Car car = carList.get(groupPosition);
        String s="Car Model: "+car.getCarModel()+"  Mileage: "+car.getMileage();
        textView.setText(s);
        final TextView branchDetails=(TextView)view.findViewById(R.id.branch_details);

        new AsyncTask<Void, Void, List<Branch>>() {
            @Override
            protected List<Branch> doInBackground(Void... params) {
                final List<Branch> branchList= Factory_Method.getBackend().getBranches();
                return branchList;

            }
            @Override
            protected void onPostExecute(List<Branch> branchList) {
                for (Branch b : branchList){
                    if(b.getBranchNumber()==car.getNumBranch())
                        branchDetails.setText(b.toString());
                }
            }
        }.execute();
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
