package com.example.talia.android5778_5956_6419_02.models.backend;


import android.content.ContentValues;

import com.example.talia.android5778_5956_6419_02.models.entities.*;

import java.util.List;

/**
 * Created by talia on 15/01/2018.
 */

public interface IDB_Manager {
    public boolean isExists(ContentValues user);//check if this user is in the DB
    public String addCustomer(ContentValues customer);
    public String addUser(ContentValues user );//add this user to the DB
    public Double updateCar(ContentValues car, int k_meters) throws Exception;//update this car with new value of kilometers
    public List<User> getUsers();//return the users list
    public List<Branch> getBranches();//return the branches list
    public List<Car> getCars();//return the cars list
    public List<Order> getOrders();//return the orders list
    public List<Car> allFreeCars();//return all free cars
    public List<Car> freeCarsForBranch(ContentValues branch);//return all free cars in this branch
    public List<Order> ordersNotClosed();//return all the orders which not closed
    public String addOrder( ContentValues order);//add a new order
    public Double closeOrder(int orderNumber , int kilometers , Double addedFuel) throws Exception;//close an exist order
    public boolean checkClosedOrder();//check if this order closed in the last 10 second
}
