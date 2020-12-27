package com.example.talia.android5778_5956_6419_02.controllers;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.talia.android5778_5956_6419_02.R;
import com.example.talia.android5778_5956_6419_02.models.backend.Factory_Method;
import com.example.talia.android5778_5956_6419_02.models.datasource.MySQL_DBManager;
import com.example.talia.android5778_5956_6419_02.models.entities.Car;
import com.example.talia.android5778_5956_6419_02.models.entities.Order;

import java.util.List;

public class FragmentYourCar extends Fragment implements View.OnClickListener {
        View view;
        Car car;
        Order reservation;
        Button closeReservation;
        Button viewCarDetailsButton;
        List<Order> orderList;

        TextView pleaseWaitTextView;
        TextView carNumberText;
        Button submitCloseButton;
        EditText closeRsrvKilometers;
        EditText closeRsrvFuelPay;
        CheckBox closeRsrvAddedFuel;
        CheckBox closeRsrvNoAddedFuel;
        LinearLayout closeRsrvFuelLayout;
        LinearLayout closeReservationLayout;

        LinearLayout openReservationLayout;
        MySQL_DBManager manager = Factory_Method.getBackend();

        OnReservationClosedListener activityListener;

/**
 * inner interface to update te menu activity that the client has closed its reservation
 */
public interface OnReservationClosedListener{
    public void closeReservation();
}
    public FragmentYourCar() {
        // Required empty public constructor
    }

    /**
     * function to check if the activity that activated the fragment has implemented the fragment interface
     * @param activity the activity that activated the fragment
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            activityListener = (OnReservationClosedListener)activity;
        }
        catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_your_car, container, false);

        closeReservation = (Button)view.findViewById(R.id.closeReservation);
        closeReservation.setOnClickListener( this );
        viewCarDetailsButton = (Button)view.findViewById(R.id.viewCarDetailsButton);
        viewCarDetailsButton.setOnClickListener( this );
        openReservationLayout = (LinearLayout)view.findViewById(R.id.openReservationLayout);
        submitCloseButton = (Button)view.findViewById(R.id.submitCloseButton);
        closeRsrvKilometers = (EditText)view.findViewById(R.id.closeRsrvKilometers);
        closeRsrvFuelPay = (EditText)view.findViewById(R.id.closeRsrvFuelPay);
        closeRsrvAddedFuel = (CheckBox)view.findViewById(R.id.closeRsrvAddedFuel);
        closeRsrvNoAddedFuel = (CheckBox)view.findViewById(R.id.closeRsrvNoAddedFuel);
        closeRsrvFuelLayout = (LinearLayout) view.findViewById(R.id.closeRsrvFuelLayout);
        closeReservationLayout = (LinearLayout)view.findViewById(R.id.closeReservationLayout);
        carNumberText = (TextView)view.findViewById(R.id.carNumberText);
        pleaseWaitTextView = (TextView)view.findViewById(R.id.pleaseWaitTextView);

        closeReservationLayout.setVisibility(View.GONE);
        closeRsrvFuelLayout.setVisibility(View.GONE);

        submitCloseButton.setOnClickListener(this);

        //ceckbox listeners to know if to open or close the added fuel layout
        closeRsrvNoAddedFuel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    closeRsrvFuelLayout.setVisibility(View.GONE);
                }
            }
        });
        closeRsrvAddedFuel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    closeRsrvFuelLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        setViewByReservation();
        return view;
    }

    /**
     * set the known info about the client reservation to the view
     * if there is an opened reservation updates the view according to reservation details
     * else tells the user there is no open reservation
     */
    private void setViewByReservation() {
        int resvNumber = -1;
        Bundle args = getArguments();
        final int id = args.getInt("ID");
        final Order o;
        //o=manager.findOrderById(id);
        new AsyncTask<Void, Void, List<Order>>() {
            @Override
            protected List<Order> doInBackground(Void... params) {
                final List<Order> orderList= Factory_Method.getBackend().getOrders();
                return orderList;

            }
            @Override
            protected void onPostExecute(List<Order> orderList) {
                for (Order order : orderList)
                    if(order.getNumCustomer() == id)
                       reservation=order;
            }
        }.execute();
       if(reservation!=null)
           resvNumber=reservation.getOrderNum();
        // / if(getArguments().containsKey("RESERVATION"))
         //   resvNumber = getArguments().getInt("RESERVATION");
        if(resvNumber == -1) //there is no open reservation for client
        {
            TextView noReservationTextView = (TextView)view.findViewById(R.id.noReservationTextView);
            noReservationTextView.setVisibility(View.VISIBLE);
            openReservationLayout.setVisibility(View.GONE);
        }
        else
        {
            openReservationLayout.setVisibility(View.VISIBLE);
            TextView noReservationTextView = (TextView)view.findViewById(R.id.noReservationTextView);
            noReservationTextView.setVisibility(View.GONE);
            reservation = manager.findOrder(resvNumber);
            //find Car
            new AsyncTask<Void, Void, List<Car>>() {
                @Override
                protected List<Car> doInBackground(Void... params) {
                    final List<Car> carList= Factory_Method.getBackend().getCars();
                    return carList;

                }
                @Override
                protected void onPostExecute(List<Car> branchList) {
                    for (Car car:manager.getCars())
                        if(car.getNumCar()==(reservation.getNumCar()))
                            carNumberText.setText(car.getNumCar());
                    }
            }.execute();

        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        if (v == closeReservation)
            closeReservationStep1();
        else if (v == submitCloseButton)
            closeReservationStep2();
        else if (v == viewCarDetailsButton)
            showCarDetails();
    }

    /**
     * the first step for closing a reservation is to open the close reservation layout
     */
    private void closeReservationStep1() {
        closeReservationLayout.setVisibility(View.VISIBLE);
    }

//      ORIGINAL FUNCTION NO ASYNCTASK
//    private void closeReservationStep2() {
//            try {
//                validateInput();
//                double pay;
//                if (closeRsrvNoAddedFuel.isChecked())
//                    pay = 0;
//                else
//                    pay = Double.valueOf(closeRsrvFuelPay.getText().toString());
//                Double payment = manager.closeReservation(reservation.getReservationNumber(),
//                        Double.valueOf(closeRsrvKilometers.getText().toString()),
//                        pay);
//                Toast.makeText(getContext(),"reservation is closed",Toast.LENGTH_LONG).show();
//                payDialog();
//            }
//            catch (Exception e){
//                Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
//                return;
//            }
//    }

    /**
     * collect the data the client filled and validates it, if all good closes the reservation
     * and let the client know what is the final payment
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void closeReservationStep2() {
        try {
            validateInput();
            final double pay; // how much the client payed for the fuel
            if (closeRsrvNoAddedFuel.isChecked()) //client didn't add fuel
                pay = 0;
            else
                pay = Double.valueOf(closeRsrvFuelPay.getText().toString());
            final int kilometers = Integer.valueOf(closeRsrvKilometers.getText().toString());
            new AsyncTask<Void, Double, Double>() {
                @Override
                protected void onPostExecute(Double aDouble) {
                    pleaseWaitTextView.setVisibility(View.GONE);
                    if (aDouble >= 0) { // if closing successful let the client know the final payment
                        payDialog(aDouble);
                        activityListener.closeReservation();
                    }
                }

                @Override
                protected Double doInBackground(Void... params) {
                    try {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pleaseWaitTextView.setVisibility(View.VISIBLE);
                            }
                        });

                        //returns the final payment
                        return manager.closeOrder(reservation.getOrderNum(), kilometers, pay);
                    } catch (final Exception e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @RequiresApi(api = Build.VERSION_CODES.M)
                            @Override
                            public void run() {
                                Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                        return -1.0; //closing failed
                    }
                }
            }.execute();
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            return;
        }
    }

    /**
     * validates the info the client provided for closing reservation
     * @return true if all the info is validated and filled correctly, else false
     * @throws Exception if there is a problem with the info provided
     */
    private boolean validateInput() throws Exception {
        String message="";
        if (!(closeRsrvAddedFuel.isChecked()) && !(closeRsrvNoAddedFuel.isChecked()))
            message = "please check one option";
        else if (closeRsrvAddedFuel.isChecked() && closeRsrvNoAddedFuel.isChecked())
            message = "please check only one option";
        else if(closeRsrvKilometers.getText().toString().isEmpty())
            message = "please fill the kilometers you drove";
        else if (Integer.valueOf(closeRsrvKilometers.getText().toString()) <= 0)
            message = "please enter validate kilometers number";
        else if(closeRsrvAddedFuel.isChecked() && closeRsrvFuelPay.getText().toString().isEmpty())
            message = "please fill the pay for the fuel";
        else if (closeRsrvAddedFuel.isChecked() && Double.parseDouble(closeRsrvFuelPay.getText().toString()) <= 0)
            message = "please enter validate pay";
        if(message.isEmpty())
            return true;
        else
            throw new Exception(message);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void payDialog(double payment) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Reservation No." + reservation.getOrderNum()+" is closed");
        String pay;
        if (payment <= 0)
            pay = "0.0";
        else
            pay = String.format("%.2f",payment);
        builder.setMessage("your final payment is "+pay+" dollars\nreceipt will be send to your email address");
        builder.create().show();
    }

    /*
    activates a dialog to show full specific car details, including branch and model
     */
    private void showCarDetails() {
       /* View view = MyCarDetailsView.getView(getContext(),car);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        builder.setTitle("full car details");
        builder.setNeutralButton("got it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        builder.show();*/
    }
}