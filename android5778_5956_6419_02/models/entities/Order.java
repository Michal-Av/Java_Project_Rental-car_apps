package com.example.talia.android5778_5956_6419_02.models.entities;

import java.util.Date;

/**
 * Created by talia on 14/01/2018.
 */

public class Order {

    protected int numCustomer;
    protected boolean openOrder;
    protected int numCar;
    protected Date startRent;
    protected Date endRent;
    protected int startMileage;
    protected int endMileage;
    protected boolean fuelFilling;
    protected Double fuel=0.0;
    protected Double payment;
    protected int orderNum;
    public static int ORDER_NUM = 10;

    public Order() {
        this.numCustomer = 0;
        this.openOrder = true;
        this.numCar = 0;
        this.startRent = new Date();
        this.endRent = new Date();
        this.startMileage = 0;
        this.endMileage = 0;
        this.fuelFilling = true;
        this.fuel = 0.0;
        this.payment = 0.0;
        this.orderNum = 0;
    }
    public Order(int numCustomer, boolean openOrder, int numCar, Date startRent, Date endRent, int startMileage,
                 int endMileage, boolean fuelFilling, Double fuel, Double payment, int orderNum) {
        this.numCustomer = numCustomer;
        this.openOrder = openOrder;
        this.numCar = numCar;
        this.startRent = startRent;
        this.endRent = endRent;
        this.startMileage = startMileage;
        this.endMileage = endMileage;
        this.fuelFilling = fuelFilling;
        this.fuel = fuel;
        this.payment = payment;
        this.orderNum = orderNum;
    }


    public int getNumCustomer() {
        return numCustomer;
    }

    public void setNumCustomer(int numCustomer) {
        this.numCustomer = numCustomer;
    }

    public boolean getOpenOrder() {
        return openOrder;
    }

    public void setOpenOrder(boolean openOrder) {
        this.openOrder = openOrder;
    }

    public int getNumCar() {
        return numCar;
    }

    public void setNumCar(int numCar) {
        this.numCar = numCar;
    }

    public Date getStartRent() {
        return startRent;
    }

    public void setStartRent(Date startRent) {
        this.startRent = startRent;
    }

    public Date getEndRent() {
        return endRent;
    }

    public void setEndRent(Date endRent) {
        this.endRent = endRent;
    }

    public int getStartMileage() {
        return startMileage;
    }

    public void setStartMileage(int startMileage) {
        this.startMileage = startMileage;
    }

    public int getEndMileage() {
        return endMileage;
    }

    public void setEndMileage(int endMileage) {
        this.endMileage = endMileage;
    }

    public boolean getFuelFilling() {
        return fuelFilling;
    }

    public void setFuelFilling(boolean fuelFilling) {
        this.fuelFilling = fuelFilling;
    }

    public Double getFuel() {
        return fuel;
    }

    public void setFuel(Double fuel) {
        this.fuel = fuel;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Order{" +
                "numCustomer=" + numCustomer +
                ", openOrder=" + openOrder +
                ", numCar=" + numCar +
                ", startRent=" + startRent +
                ", endRent=" + endRent +
                ", startMileage=" + startMileage +
                ", endMileage=" + endMileage +
                ", fuelFilling=" + fuelFilling +
                ", fuel=" + fuel +
                ", payment=" + payment +
                ", orderNum=" + orderNum +
                '}';
    }
}

