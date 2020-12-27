package com.example.talia.android5778_5956_6419_02.models.backend;
import android.content.ContentValues;

import android.content.ContentValues;

import com.example.talia.android5778_5956_6419_02.models.entities.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by talia on 15/01/2018.
 */

public class Const {

    public static class UserConst {
        public static final String USER_NAME = "userName";
        public static final String USER_PASSWORD = "userPassword";
    }

    public static class CarConst {
        public static final String NUM_BRANCH = "numBranch";
        public static final String CAR_MODEL = "carModel";
        public static final String MILEAGE = "mileage";
        public static final String NUM_CAR = "numCar";
    }

    public static class BranchConst {
        public static final String ADDRESS = "address";
        public static final String NUM_PARKING_SPACE = "numParkingSpaces";
        public static final String BRANCH_NUMBER = "branchNumber";
    }

    public static class CustomerConst {
        public static final String LAST_NAME = "lastName";
        public static final String FIRST_NAME = "firstName";
        public static final String ID = "id";
        public static final String PASSWORD = "password";
        public static final String PHONE_NUMBER = "phoneNumber";
        public static final String MAIL = "mail";
        public static final String CREDIT_CARD = "creditCard";
    }

    public static class OrderConst {
        public static final String NUM_CUSTOMER = "numCustomer";
        public static final String OPEN_ORDER = "openOrder";
        public static final String NUM_CAR = "numCar";
        public static final String START_RENT = "startRent";
        public static final String END_RENT = "endRent";
        public static final String START_MILEAGE = "startMileage";
        public static final String END_MILEAGE = "endMileage";
        public static final String FUEL_FILLING = "fuelFilling";
        public static final String FUEL = "fuel";
        public static final String PAYMENT = "payment";
        public static final String ORDER_NUM = "orderNum";
    }

    public static ContentValues userToContentValue(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserConst.USER_NAME, user.getUserName());
        contentValues.put(UserConst.USER_PASSWORD, user.getUserPassword());
        return contentValues;
    }

    public static User contentValueToUser(ContentValues contentValues) {
        User user = new User();
        user.setUserName((contentValues.getAsString(UserConst.USER_NAME)));
        user.setUserPassword((contentValues.getAsString(UserConst.USER_PASSWORD)));
        return user;
    }

    public static ContentValues CarToContentValue(Car car) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CarConst.NUM_BRANCH, car.getNumBranch());
        contentValues.put(CarConst.CAR_MODEL, car.getCarModel());
        contentValues.put(CarConst.MILEAGE, car.getMileage());
        contentValues.put(CarConst.NUM_CAR, car.getNumCar());
        return contentValues;
    }

    public static Car contentValueToCar(ContentValues contentValues) {
        Car car = new Car();
        car.setNumBranch(contentValues.getAsInteger(CarConst.NUM_BRANCH));
        car.setCarModel(contentValues.getAsInteger(CarConst.CAR_MODEL));
        car.setMileage(contentValues.getAsInteger(CarConst.MILEAGE));
        car.setNumCar(contentValues.getAsInteger(CarConst.NUM_CAR));
        return car;
    }

    public static ContentValues BranchToContentValue(Branch branch) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BranchConst.ADDRESS, branch.getAddress());
        contentValues.put(BranchConst.NUM_PARKING_SPACE, branch.getNumParkingSpaces());
        contentValues.put(BranchConst.BRANCH_NUMBER, branch.getBranchNumber());
        return contentValues;
    }
    public static Branch contentValueToBranch(ContentValues contentValues) {
        Branch branch = new Branch();
        branch.setAddress(contentValues.getAsString(BranchConst.ADDRESS));
        branch.setNumParkingSpaces(contentValues.getAsInteger(BranchConst.NUM_PARKING_SPACE));
        branch.setBranchNumber(contentValues.getAsInteger(BranchConst.BRANCH_NUMBER));
        return branch;
    }
    public static ContentValues OrderToContentValue(Order order) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(OrderConst.NUM_CUSTOMER, order.getNumCustomer());
        contentValues.put(OrderConst.OPEN_ORDER, order.getOpenOrder());
        contentValues.put(OrderConst.NUM_CAR, order.getNumCar());

        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString=dateFormat.format(order.getStartRent());
        contentValues.put(OrderConst.START_RENT,dateString);

        DateFormat dateFormat1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateString=dateFormat1.format(order.getEndRent());
        contentValues.put(OrderConst.END_RENT,dateString);

        contentValues.put(OrderConst.START_MILEAGE, order.getStartMileage());
        contentValues.put(OrderConst.END_MILEAGE, order.getEndMileage());
        contentValues.put(OrderConst.FUEL_FILLING, order.getFuelFilling());
        contentValues.put(OrderConst.FUEL, order.getFuel());
        contentValues.put(OrderConst.PAYMENT, order.getPayment());
        contentValues.put(OrderConst.ORDER_NUM, order.getOrderNum());
        return contentValues;
    }
    public static Order contentValueToOrder(ContentValues contentValues) {
        Order order = new Order();
        order.setNumCustomer(contentValues.getAsInteger(OrderConst.NUM_CUSTOMER));
        order.setNumCar(contentValues.getAsInteger(OrderConst.NUM_CAR));
        //order.setStartRent(contentValues.getAs(OrderConst.START_RENT));
        // order.setEndRent(contentValues.getAsInteger(OrderConst.END_RENT));
        order.setStartMileage(contentValues.getAsInteger(OrderConst.START_MILEAGE));
        order.setEndMileage(contentValues.getAsInteger(OrderConst.END_MILEAGE));
        order.setFuelFilling(contentValues.getAsBoolean(OrderConst.FUEL_FILLING));
        order.setFuel(contentValues.getAsDouble(OrderConst.FUEL));
        order.setPayment(contentValues.getAsDouble(OrderConst.PAYMENT));
        order.setOrderNum(contentValues.getAsInteger(OrderConst.ORDER_NUM));

        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString=contentValues.getAsString(OrderConst.START_RENT);
        try{
            order.setStartRent(dateFormat.parse(dateString));
        }catch (ParseException e){e.printStackTrace();}

        String dateString1=contentValues.getAsString(OrderConst.END_RENT);
        try{
            order.setEndRent(dateFormat.parse(dateString1));
        }catch (ParseException e){e.printStackTrace();}
        return order;
    }
    public static ContentValues CustomerToContentValue(Customer customer) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CustomerConst.LAST_NAME, customer.getLastName());
        contentValues.put(CustomerConst.FIRST_NAME, customer.getFirstName());
        contentValues.put(CustomerConst.ID, customer.getId());
        contentValues.put(CustomerConst.PASSWORD, customer.getPassword());
        contentValues.put(CustomerConst.PHONE_NUMBER, customer.getPhoneNumber());
        contentValues.put(CustomerConst.MAIL, customer.getMail());
        contentValues.put(CustomerConst.CREDIT_CARD, customer.getCreditCard());
        return contentValues;
    }
    public static Customer contentValueToCustomer(ContentValues contentValues) {
        Customer customer = new Customer();
        customer.setLastName(contentValues.getAsString(CustomerConst.LAST_NAME));
        customer.setFirstName(contentValues.getAsString(CustomerConst.FIRST_NAME));
        customer.setId(contentValues.getAsInteger(CustomerConst.ID));
        customer.setPassword(contentValues.getAsString(CustomerConst.PASSWORD));
        customer.setPhoneNumber(contentValues.getAsInteger(CustomerConst.PHONE_NUMBER));
        customer.setMail(contentValues.getAsString(CustomerConst.MAIL));
        customer.setCreditCard(contentValues.getAsInteger(CustomerConst.CREDIT_CARD));
        return customer;
    }
}


