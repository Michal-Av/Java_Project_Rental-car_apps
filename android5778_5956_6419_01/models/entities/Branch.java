package com.example.talia.android5778_5956_6419_01.models.entities;

/**
 * Created by talia on 14/11/2017.
 */

public class Branch {

    protected String address;
    protected int numParkingSpaces;
    protected int branchNumber;

    public Branch() {
        this.address = "";
        this.numParkingSpaces =0;
        this.branchNumber =0;
    }
    public Branch(String address, int numParkingSpaces, int branchNumber) {
        this.address = address;
        this.numParkingSpaces = numParkingSpaces;
        this.branchNumber = branchNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumParkingSpaces() {
        return numParkingSpaces;
    }

    public void setNumParkingSpaces(int numParkingSpaces) {
        this.numParkingSpaces = numParkingSpaces;
    }

    public int getBranchNumber() {
        return branchNumber;
    }

    public void setBranchNumber(int branchNumber) {
        this.branchNumber = branchNumber;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "address='" + address + '\'' +
                ", numParkingSpaces=" + numParkingSpaces +
                ", branchNumber=" + branchNumber +
                '}';
    }
}
