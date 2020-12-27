package com.example.talia.android5778_5956_6419_01.models.entities;

/**
 * Created by talia on 14/11/2017.
 */

public class Car {
    protected int numBranch;
    protected int carModel;
    protected int mileage;
    protected int numCar;

    public Car() {
        this.numBranch = 0;
        this.carModel = 0;
        this.mileage = 0;
        this.numCar = 0;
    }
    public Car(int numBranch, int carModel, int mileage, int numCar) {
        this.numBranch = numBranch;
        this.carModel = carModel;
        this.mileage = mileage;
        this.numCar = numCar;
    }


    public int getNumBranch() {
        return numBranch;
    }

    public void setNumBranch(int numBranch) {
        this.numBranch = numBranch;
    }

    public int getCarModel() {
        return carModel;
    }

    public void setCarModel(int carModel) {
        this.carModel = carModel;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getNumCar() {
        return numCar;
    }

    public void setNumCar(int numCar) {
        this.numCar = numCar;
    }
    @Override
    public String toString() {
        return "Car{" +
                "numBranch=" + numBranch +
                ", carModel=" + carModel +
                ", mileage=" + mileage +
                ", numCar=" + numCar +
                '}';
    }
}
