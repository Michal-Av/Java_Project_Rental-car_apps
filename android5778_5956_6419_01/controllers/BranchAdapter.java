package com.example.talia.android5778_5956_6419_01.controllers;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.talia.android5778_5956_6419_01.R;
import com.example.talia.android5778_5956_6419_01.models.entities.Branch;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Objects;

/**
 * Created by talia on 15/01/2018.
 */

public class BranchAdapter extends BaseAdapter {
    List<Branch> list;
    LayoutInflater layoutInflater;

    private TextView branchNumber;
    private TextView address;
    private TextView numParkingSpaces;

    private void findViews (View view)
    {
        branchNumber=(TextView)view.findViewById(R.id.branchNumber);
        address=(TextView)view.findViewById(R.id.address);
        numParkingSpaces=(TextView)view.findViewById(R.id.numParkingSpaces);
    }
    public BranchAdapter (Context context, List<Branch> lst)
    {
        list= lst;
        layoutInflater = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount(){return list.size();}

    @Override
    public Object getItem (int position)
    {
        return list.get(position);
    }
    @Override
    public long getItemId (int position)
    {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = layoutInflater.inflate(R.layout.branch_item, null);
        findViews(view);
        Branch branch = list.get(position);
        branchNumber.setText(String.valueOf(branch.getBranchNumber()));
        address.setText(branch.getAddress());
        numParkingSpaces.setText(String.valueOf(branch.getNumParkingSpaces()));
        return view;
    }
}
