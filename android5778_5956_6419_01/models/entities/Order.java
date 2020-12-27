package com.example.talia.android5778_5956_6419_01.models.entities;

import java.util.Date;

/**
 * Created by talia on 14/11/2017.
 */

public class Order {

    protected int numCustomer;
    protected boolean openOrder;
    protected int numCar;
    protected Date startRent;
    protected Date endRent;
    protected Double startMileage;
    protected Double endMileage;
    protected boolean fuelFilling;
    protected Double fuel=0.0;
    protected Double payment;
    protected int orderNum;

    public Order(int numCustomer, boolean openOrder, int numCar, Date startRent, Date endRent, Double startMileage,
                 Double endMileage, boolean fuelFilling, Double fuel, Double payment, int orderNum) {
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

    public boolean isOpenOrder() {
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

    public Double getStartMileage() {
        return startMileage;
    }

    public void setStartMileage(Double startMileage) {
        this.startMileage = startMileage;
    }

    public Double getEndMileage() {
        return endMileage;
    }

    public void setEndMileage(Double endMileage) {
        this.endMileage = endMileage;
    }

    public boolean isFuelFilling() {
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
