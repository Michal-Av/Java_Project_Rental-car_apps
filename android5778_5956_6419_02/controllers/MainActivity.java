package com.example.talia.android5778_5956_6419_02.controllers;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.talia.android5778_5956_6419_02.controllers.*;
import com.example.talia.android5778_5956_6419_02.R;
import com.example.talia.android5778_5956_6419_02.models.backend.Factory_Method;
import com.example.talia.android5778_5956_6419_02.models.backend.MyService;
import com.example.talia.android5778_5956_6419_02.models.backend.Receiver;
import com.example.talia.android5778_5956_6419_02.models.datasource.MySQL_DBManager;
import com.example.talia.android5778_5956_6419_02.models.entities.Branch;
import com.example.talia.android5778_5956_6419_02.models.entities.Customer;
import com.example.talia.android5778_5956_6419_02.models.entities.Order;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Order order;
    Customer customer;
    MySQL_DBManager manager = Factory_Method.getBackend();
    Receiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiver = new Receiver();
        IntentFilter filter = new IntentFilter("CAR_CHANGE");
        registerReceiver(receiver,filter);
        startService(new Intent(this, MyService.class));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        updateViewByClient();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "By Michal and Talia", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

  //  private void updateViewByClient() {
    //
     //       Intent intent = getIntent();
       //     int id = intent.getIntExtra("ID", 0);
         //   if (id == 0)
           //     return;
        //}

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_free_cars) {
            // Handle the camera action
            freeCarsFragment();
        } else if (id == R.id.nav_branches) {
            branchesFragment();
        } else if (id == R.id.nav_client_car) {
            yourCarFragment();
        } else if (id == R.id.nav_company_info) {
            aboutCompanyFragment();
        } else if (id == R.id.nav_log_out) {
            finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void updateViewByClient() {
        Intent intent = getIntent();
        final int id = intent.getIntExtra("ID",0);
        if(id == 0)
            return;
        NavigationView navigation = (NavigationView) findViewById(R.id.nav_view);
        View view = navigation.getHeaderView(0);
        final TextView userDataViewMenu = (TextView) view.findViewById(R.id.userData);
        final TextView userEmailViewMenu = (TextView) view.findViewById(R.id.userEmail);
        //customer = manager.findCustomer(id);
        new AsyncTask<Void,Void,Customer>(){

            @Override
            protected void onPostExecute(Customer result) {
                super.onPostExecute(result);
                if (result !=null)//message for the user
                    customer=result;
                if(customer == null)
                    return;
                order = manager.openReservationForCustomer(id);
                String firstName = customer.getFirstName();
                String lastName = customer.getLastName();
                String fullName = firstName+" "+lastName;
                userDataViewMenu.setText(fullName);
                userEmailViewMenu.setText(customer.getMail().toLowerCase());

            }

            @Override
            protected Customer doInBackground(Void... params) {
                return Factory_Method.getBackend().findCustomer(id);
            }

        }.execute();


        FragmentWelcome fragment = new FragmentWelcome();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main,fragment).commit();
    }

    private void freeCarsFragment() {
        FragmentFreeCars fragment = new FragmentFreeCars();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main,fragment).commit();

    }

    private void branchesFragment() {
        FragmentBranches fragment = new FragmentBranches();
        Bundle args = new Bundle();
        args.putInt("ID",customer.getId());
        fragment.setArguments(args);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content_main,fragment).commit();
    }

    private void yourCarFragment() {
        FragmentYourCar fragment = new FragmentYourCar();
        Bundle args = new Bundle();
        args.putInt("ID",customer.getId());
        fragment.setArguments(args);


        //Bundle args = new Bundle();
        //if(order != null)
          //  args.putInt("RESERVATION",order.getOrderNum());
        //else
         //   args.putInt("RESERVATION",-1);

        //fragment.setArguments(args);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content_main,fragment).commit();
        order = Factory_Method.getBackend().openReservationForCustomer(customer.getId());//if the reservation was closed, its updated here;
    }

    private void aboutCompanyFragment() {
        FragmentAboutUs fragment = new FragmentAboutUs();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content_main,fragment).commit();
    }

}

