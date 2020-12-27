package com.example.talia.android5778_5956_6419_01.models.backend;

import com.example.talia.android5778_5956_6419_01.models.entities.User;
import com.example.talia.android5778_5956_6419_01.models.entities.Branch;
import com.example.talia.android5778_5956_6419_01.models.entities.Car;
import com.example.talia.android5778_5956_6419_01.models.entities.CarModel;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.List;

/**
 * Created by michalus.av on 10/12/2017.
 */

public interface IDBManager {
    public List<User> getUser();
    public List<Branch> getBranch();
    public List<CarModel> getCarModels();
    public List<Car> getCar();
    public boolean userExist(ContentValues user);
    public boolean addUser(ContentValues user) throws Exception;;
    public boolean addCarModel(ContentValues contentValues) throws Exception;;
    public boolean addCar(ContentValues contentValues) throws Exception;;
}
