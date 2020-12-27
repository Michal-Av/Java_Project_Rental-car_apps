package com.example.talia.android5778_5956_6419_01.models.entities;

/**
 * Created by talia on 14/11/2017.
 */

public class CarModel {
    protected int modelCode;
    protected String companyName;
    protected String modelName;
    protected Double engineCapacity;
    protected Gearbox gearbox;
    protected int seats;

    public CarModel(){
        this.modelCode = 0;
        this.companyName = "";
        this.modelName ="";
        this.engineCapacity = 0.0;
        this.gearbox =Gearbox.AUTOMATIC;
        this.seats = 0;
    }
    public CarModel(int modelCode, String companyName, String modelName, Double engineCapacity, Gearbox gearbox, int seats) {
        this.modelCode = modelCode;
        this.companyName = companyName;
        this.modelName = modelName;
        this.engineCapacity = engineCapacity;
        this.gearbox = gearbox;
        this.seats = seats;
    }

    public int getModelCode() {
        return modelCode;
    }

    public void setModelCode(int modelCode) {
        this.modelCode = modelCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(Double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "CarModelActivity{" +
                "modelCode=" + modelCode +
                ", companyName='" + companyName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", engineCapacity=" + engineCapacity +
                ", gearbox=" + gearbox +
                ", seats=" + seats +
                '}';
    }
}
