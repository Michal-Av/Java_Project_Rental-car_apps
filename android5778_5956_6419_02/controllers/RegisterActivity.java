package com.example.talia.android5778_5956_6419_02.controllers;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.talia.android5778_5956_6419_02.R;
import com.example.talia.android5778_5956_6419_02.models.backend.Const;
import com.example.talia.android5778_5956_6419_02.models.backend.Factory_Method;
import com.example.talia.android5778_5956_6419_02.models.datasource.MySQL_DBManager;
import com.example.talia.android5778_5956_6419_02.models.entities.*;

import static com.example.talia.android5778_5956_6419_02.models.backend.Const.CustomerToContentValue;

public class RegisterActivity extends Activity implements View.OnClickListener {
    private boolean flag;
    private EditText textCustomerID;
    private EditText textPrivateName;
    private EditText textLastName;
    private EditText textPassword;
    private EditText textCustomerPhone;
    private EditText textCustomerEmail;
    private EditText textCreditNumber;
    private Button buttonNext;


    private MySQL_DBManager manager;

    private void findViews() {
        textCustomerID = (EditText)findViewById( R.id.textCustomerID );
        textPrivateName = (EditText)findViewById( R.id.textPrivateName );
        textLastName = (EditText)findViewById( R.id.textLastName );
        textPassword = (EditText)findViewById( R.id.textPassword );
        textCustomerPhone = (EditText)findViewById( R.id.textCustomerPhone );
        textCustomerEmail = (EditText)findViewById( R.id.textCustomerEmail );
        textCreditNumber = (EditText)findViewById( R.id.textCreditNumber );
        buttonNext = (Button)findViewById( R.id.buttonNext );

        buttonNext.setOnClickListener( this );
        //registerButton.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if (v == buttonNext) {
           collectCustomerData();
        }
    }

    /**
     * collect all the info of the client (not userClient) and validates it
     * if all info is correctly written, moves on to fill all userClient info and register
     */
    public boolean checkExist(int _id) {
        final int id=_id;
        new AsyncTask<Void, Void, Boolean>() {//all the act which conected with the backend will commited in AsyncTask
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                if (result)
                  //register this customer
                    flag=false;
                else
                    //print: the id exists!. stay in this window
                    flag=true;
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                return Factory_Method.getBackend().existCustomer(id);
            }
        }.execute();
        return  flag;
    }

    private void collectCustomerData() {
        final Customer customer= new Customer();
        String id = textCustomerID.getText().toString();
        String name = textPrivateName.getText().toString();
        String lastName = textLastName.getText().toString();
        String password = textPassword.getText().toString();
        String email = textCustomerEmail.getText().toString();


        //validating input
        if (id.length() == 0 || name.length() == 0||  lastName.length() == 0 ||
                password.length() == 0 || email.length() == 0 ) {
            Toast.makeText(getBaseContext(), "please fill all fields",Toast.LENGTH_SHORT).show();
            return;
        }
        int _id =0;
        try {
            _id = Integer.parseInt(id);
        }catch(Exception e){
            Toast.makeText(getBaseContext(), "ID must contain only letters",Toast.LENGTH_SHORT).show();
        }
        if(checkExist(_id))
        {
            Toast.makeText(getBaseContext(), "המשתמש קיים במערכת",Toast.LENGTH_LONG).show();
            return;
        }


        customer.setId(_id);
        customer.setFirstName(name);
        customer.setLastName(lastName);
        customer.setPassword(password);
        customer.setCreditCard(Integer.valueOf(textCreditNumber.getText().toString()));
        customer.setMail(email);
        customer.setPhoneNumber(Integer.valueOf(textCustomerPhone.getText().toString()));

        final ContentValues c = CustomerToContentValue(customer);
        new AsyncTask<Void, Void, Boolean>() {//all the act which conected with the backend will commited in AsyncTask
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                if (result)
                {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.putExtra(Const.CustomerConst.LAST_NAME, customer.getLastName());
                    intent.putExtra(Const.CustomerConst.FIRST_NAME, customer.getFirstName());
                    intent.putExtra(Const.CustomerConst.ID, customer.getId());
                    intent.putExtra(Const.CustomerConst.PASSWORD, customer.getPassword());
                    intent.putExtra(Const.CustomerConst.PHONE_NUMBER, customer.getPhoneNumber());
                    intent.putExtra(Const.CustomerConst.MAIL, customer.getMail());
                    intent.putExtra(Const.CustomerConst.CREDIT_CARD, customer.getCreditCard());
                    Toast.makeText(getBaseContext(), "המשתמש נוסף למערכת",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    Factory_Method.getBackend().addCustomer(c);
                    return true;
                }
                catch (final Exception e) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();}});
                    return false;
                }
            }
        }.execute();




    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
       // customer = new Customer();
        manager = Factory_Method.getBackend();
        findViews();
    }
}