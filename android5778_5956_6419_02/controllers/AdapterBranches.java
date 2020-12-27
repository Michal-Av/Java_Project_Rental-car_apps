package com.example.talia.android5778_5956_6419_02.controllers;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.talia.android5778_5956_6419_02.R;
import com.example.talia.android5778_5956_6419_02.models.backend.Factory_Method;
import com.example.talia.android5778_5956_6419_02.models.datasource.MySQL_DBManager;
import com.example.talia.android5778_5956_6419_02.models.entities.Branch;
import com.example.talia.android5778_5956_6419_02.models.entities.Car;
import com.example.talia.android5778_5956_6419_02.models.entities.Order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.talia.android5778_5956_6419_02.models.backend.Const.BranchToContentValue;
import static com.example.talia.android5778_5956_6419_02.models.backend.Const.CarToContentValue;
import static com.example.talia.android5778_5956_6419_02.models.backend.Const.OrderToContentValue;
import static java.security.AccessController.getContext;

/**
 * Created by michalus.av on 15/02/2018.
 */

public class AdapterBranches extends BaseExpandableListAdapter implements Filterable {

    List<Branch> branchList;
    List<Branch> originalBranchList; // keeps the original list, while branchList is changing according to filter values
    MySQL_DBManager manager;
    List<Car> carList;
    Context context;
    LayoutInflater mInflater;
    Car car;//the car which selected in the spinner for doing order
    Order order= new Order();
    int id;


    public AdapterBranches(Context context,List<Branch> branchList, int _id) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        manager = Factory_Method.getBackend();
        this.branchList = branchList;
        originalBranchList = branchList;
        id=_id;
    }



    @Override
    public int getGroupCount() {
        return branchList.size();
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
        View view = mInflater.inflate(R.layout.expnd_list_header,parent,false);
        TextView branchNumberHeader = (TextView)view.findViewById(R.id.branchNumberHeader);
        TextView branchParkingsHeader = (TextView)view.findViewById(R.id.branchParkingsHeader);
        Branch branch = branchList.get(groupPosition);
        branchNumberHeader.setText(String.valueOf(branch.getBranchNumber()));
        branchParkingsHeader.setText(String.valueOf(branch.getNumParkingSpaces()));
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.expnd_list_item,parent,false);
        final Branch branch = branchList.get(groupPosition);

        final Button addOrder=(Button)view.findViewById(R.id.add_order);
        final Spinner carSpinner =(Spinner)view.findViewById(R.id.carSpinner);
        final TextView branchCarsNumber = (TextView)view.findViewById(R.id.branchCarsNumberView);
        TextView branchNumber = (TextView)view.findViewById(R.id.branchNumberView);
        TextView branchAddress = (TextView)view.findViewById(R.id.branchAddress);
        TextView branchParkingSpaces = (TextView)view.findViewById(R.id.branchParkingSpaces);
      //  final ListView carListInExpndList = (ListView)view.findViewById(R.id.carListInExpndList);

       /*
        //checking is done because adapter is set for a list with values
        if (carList.size() != 0)
            carListInExpndList.setAdapter(new AdapterListView(carList, context));
        //allows to scroll the internal list view
        carListInExpndList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });*/

        new AsyncTask<Void, Void, ArrayAdapter>() {

            @Override
            protected ArrayAdapter doInBackground(Void... params) {
                final List<Car> spinnerArray= manager.freeCarsForBranch(BranchToContentValue(branch));
                carList=spinnerArray;
                return new ArrayAdapter<Car>(context,android.R.layout.simple_spinner_dropdown_item,spinnerArray);
            }
            @Override
            protected void onPostExecute(ArrayAdapter adapter) {
                if(adapter.isEmpty())
                    addOrder.setVisibility(View.GONE);
                branchCarsNumber.setText(String.valueOf(carList.size()));
                carSpinner.setAdapter(adapter);

            }
        }.execute();

        carSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                car = (Car)parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                addOrder.setVisibility(View.GONE);
            }
        });


        addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                addNewOrder();
            }
        });
        branchNumber.setText(String.valueOf(branch.getBranchNumber()));
        branchAddress.setText(String.valueOf(branch.getAddress()));
        branchParkingSpaces.setText(String.valueOf(branch.getNumParkingSpaces()));

        return view;
    }
    private void addNewOrder()
    {
        order.setNumCar(car.getNumCar());
        order.setStartMileage(car.getMileage());
        order.setNumCustomer(id);
        dialogBeforeAdding(car.getNumCar(),order);

    }

    private void dialogBeforeAdding(final int numCar, final Order order){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.ic_menu_info);
        builder.setTitle("Are you sure?");
        String message = "a new reservation for car number "+numCar;
        builder.setMessage(message);
        AlertDialog.OnClickListener onClickListener = new AlertDialog.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case AlertDialog.BUTTON_NEGATIVE:
                    break;
                case AlertDialog.BUTTON_POSITIVE:
                    try {
                        new AsyncTask<Void, Integer, Integer>() {
                            @Override
                            protected void onPostExecute(Integer integer) {
                                if(integer != 0) {                                  // if managed to open reservation
                         //           FragmentBranches.activityListener.newReservationForClient(order); // inform menu activity about the change
                                    dialogAfterAdding(numCar, integer);          // confirm reservation number to client
                                }
                            }

                            @Override
                            protected Integer doInBackground(Void... params) {
                                    int resvNumber = manager.addReservation(order);
                                    return resvNumber;
                                }

                                //return 0;

                        }.execute();
                    } catch (Exception e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
            }
        }
    };

        builder.setNegativeButton("No",onClickListener);
        builder.setPositiveButton("Yes",onClickListener);
        builder.show();
}

    private void dialogAfterAdding(int carNumber, int resvNumber) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.ic_action_check);
        builder.setTitle("Reservation Opened");
        String message = "reservation number " + resvNumber + " opened \n for car number " + carNumber + " in your name";
        builder.setMessage(message);
        AlertDialog.OnClickListener onClickListener = new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        };
        builder.setNeutralButton("Got it", onClickListener);
        builder.show();
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            /**
             * private function to check if a CharSequence is formed from only digits
             * the func purpose is to help know the filter type of data so it know where to look for the word
             * @param word a parameter to check if fully formed from digits
             * @return true if only digits
             */
            private boolean onlyDigits(CharSequence word)
            {
                try{
                    Integer.parseInt(word.toString());
                    return true;
                }
                catch (Exception e){
                    return false;
                }
            }

            /**
             * private function to check if a CharSequence is formed from only letters
             * the func purpose is to help know the filter type of data so it know where to look for the word
             * @param word a parameter to check if fully formed from letters
             * @return true if only letters
             */
            private boolean onlyLetters(CharSequence word){
                for (int i=0 ;i<word.length();i++)
                    try{
                        Integer.parseInt(String.valueOf(word.charAt(i)));
                        return false;
                    }
                    catch (Exception e){}
                return true;
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint == null){
                    results.values = branchList;
                    results.count = branchList.size();
                }
                else {
                    List<Branch> mBranchList = new ArrayList<>();
                    /*we look for results in te original branch list so it includes all the branch that
                     satisfies the constraint and nort only the ones that in the already filtered list
                     */
                    for (Branch b:originalBranchList)
                    {
                        if(onlyLetters(constraint)) {
                            if (b.getAddress().toLowerCase().contains(constraint))
                                mBranchList.add(b);
                        }
                        else if(onlyDigits(constraint)){
                            if(String.valueOf(b.getBranchNumber()).contains(constraint) ||
                                    String.valueOf(b.getAddress()).contains(constraint))
                                mBranchList.add(b);
                        }
                    }
                    results.values = mBranchList;
                    results.count = mBranchList.size();
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.count == 0)
                    notifyDataSetInvalidated();
                else{
                    branchList = (List<Branch>)results.values;
                    notifyDataSetChanged();
                }
            }
        };
    }


}
