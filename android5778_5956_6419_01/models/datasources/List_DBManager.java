package com.example.talia.android5778_5956_6419_01.models.datasources;

import com.example.talia.android5778_5956_6419_01.models.backend.Const;
import com.example.talia.android5778_5956_6419_01.models.backend.IDBManager;
import com.example.talia.android5778_5956_6419_01.models.entities.Branch;
import com.example.talia.android5778_5956_6419_01.models.entities.Car;
import com.example.talia.android5778_5956_6419_01.models.entities.CarModel;
import com.example.talia.android5778_5956_6419_01.models.entities.User;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
import static com.example.talia.android5778_5956_6419_01.models.backend.Const.*;
/**
 * Created by michalus.av on 10/12/2017.
 */
/*
public class List_DBManager implements IDBManager {

    static List<User> users;
    static  List<Branch> branches;
    static List<Car> cars;
    static List<CarModelActivity> carModels;
    static {
        users = new ArrayList<>();
        branches = new ArrayList<>();
        cars = new ArrayList<>();
        carModels = new ArrayList<>();
    }

    @Override
    public List<CarModelActivity> getCarModels()
    {
        for(CarModelActivity item: carModels)
         carModels.add(item);
        return carModels;
    }

    @Override
    public List<User> getUser()
    {
        for(User item : users)
            users.add(item);
        return users;
    }

    @Override
    public List<Branch> getBranch() {
        return branches;
    }

    @Override
    public List<Car> getCar()
    {
      return cars;
    }

    @Override
    public void addUser(ContentValues contentValues) {
        User user = contentValueToUser(contentValues);
        users.add(user);
        return;
    }
    @Override
    public boolean userExist(ContentValues user) {
        User u = Const.contentValueToUser(user);
        for(User item : users)
            if(item==u)
                return true;
        return false;
    }
    @Override
    public void addCarModel(ContentValues contentValues) {
        CarModelActivity carModel = contentValueToCarModel(contentValues);
        carModels.add(carModel);
    }

    @Override
    public void addCar(ContentValues contentValues) {
        Car car = contentValueToCar(contentValues);
        cars.add(car);
    }


}


*/