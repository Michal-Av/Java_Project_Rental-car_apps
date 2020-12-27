package com.example.talia.android5778_5956_6419_01.models.backend;

import android.content.ContentValues;
import com.example.talia.android5778_5956_6419_01.models.entities.*;

/**
 * Created by michalus.av on 29/11/2017.
 */

public class Const {
    public static class UserConst{
        public static final String USER_NAME="userName";
        public static final String USER_PASSWORD="userPassword";
    }
    public static class CarConst{
        public static final String NUM_BRANCH="numBranch";
        public static final String CAR_MODEL="carModel";
        public static final String MILEAGE="mileage";
        public static final String NUM_CAR="numCar";
    }
    public static class CarModelConst{
        public static final String MODEL_CODE="modelCode";
        public static final String COMPANY_NAME="companyName";
        public static final String MODEL_NAME="modelName";
        public static final String ENGINE_CAPACITY="engineCapacity";
        public static final String GEARBOX="gearbox";
        public static final String SEATS="seats";
    }

    public static ContentValues userToContentValue(User user)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserConst.USER_NAME, user.getUserName());
        contentValues.put(UserConst.USER_PASSWORD, user.getUserPassword());
        return contentValues;
    }
    public static User contentValueToUser (ContentValues contentValues)
    {
        User user = new User();
        user.setUserName((contentValues.getAsString(UserConst.USER_NAME)));
        user.setUserPassword((contentValues.getAsString(UserConst.USER_PASSWORD)));
        return user;
    }
    public static ContentValues CarToContentValue(Car car)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CarConst.NUM_BRANCH, car.getNumBranch());
        contentValues.put(CarConst.CAR_MODEL, car.getCarModel());
        contentValues.put(CarConst.MILEAGE, car.getMileage());
        contentValues.put(CarConst.NUM_CAR, car.getNumCar());
        return contentValues;
    }
    public static Car contentValueToCar (ContentValues contentValues)
    {
        Car car = new Car();
        car.setNumBranch(contentValues.getAsInteger(CarConst.NUM_BRANCH));
        car.setCarModel(contentValues.getAsInteger(CarConst.CAR_MODEL));
        car.setMileage(contentValues.getAsInteger(CarConst.MILEAGE));
        car.setNumCar(contentValues.getAsInteger(CarConst.NUM_CAR));
        return car;
    }
    public static ContentValues CarModelToContentValue(CarModel carModel)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CarModelConst.MODEL_CODE, carModel.getModelCode());
        contentValues.put(CarModelConst.COMPANY_NAME, carModel.getCompanyName());
        contentValues.put(CarModelConst.MODEL_NAME, carModel.getModelName());
        contentValues.put(CarModelConst.ENGINE_CAPACITY, carModel.getEngineCapacity());
        contentValues.put(CarModelConst.GEARBOX, carModel.getGearbox().toString());
        contentValues.put(CarModelConst.SEATS, carModel.getSeats());
        return contentValues;
    }
    public static CarModel contentValueToCarModel (ContentValues contentValues)
    {
        CarModel carModel = new CarModel();
        carModel.setModelCode(contentValues.getAsInteger(CarModelConst.MODEL_CODE));
        carModel.setCompanyName(contentValues.getAsString(CarModelConst.COMPANY_NAME));
        carModel.setModelName(contentValues.getAsString(CarModelConst.MODEL_NAME));
        carModel.setEngineCapacity(contentValues.getAsDouble(CarModelConst.ENGINE_CAPACITY));
        carModel.setGearbox(Gearbox.valueOf(contentValues.getAsString(CarModelConst.GEARBOX)));
        carModel.setSeats(contentValues.getAsInteger(CarModelConst.SEATS));
        return carModel;
    }

}
