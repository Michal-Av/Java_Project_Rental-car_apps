package com.example.talia.android5778_5956_6419_01.models.datasources;

import android.content.ContentValues;
import android.util.Log;

import com.example.talia.android5778_5956_6419_01.models.backend.Const;
import com.example.talia.android5778_5956_6419_01.models.backend.IDBManager;
import com.example.talia.android5778_5956_6419_01.models.entities.Branch;
import com.example.talia.android5778_5956_6419_01.models.entities.Car;
import com.example.talia.android5778_5956_6419_01.models.entities.CarModel;
import com.example.talia.android5778_5956_6419_01.models.entities.User;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import com.example.talia.android5778_5956_6419_01.models.datasources.PHPtools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

/**
 * Created by talia on 03/01/2018.
 */

public class MySQL_DBManager implements IDBManager{
private String WEB_URL="http://tsameret.vlab.jct.ac.il";
    private boolean updateFlag = false;
    private void SetUpdate()
    {
        updateFlag = true;
    }

    public void printLog(String message)
    {
        Log.d(this.getClass().getName(),"\n"+message);
    }
    public void printLog(Exception message)
    {
        Log.d(this.getClass().getName(),"Exception-->\n"+message);
    }

    @Override
 public List<User> getUser(){
List<User> users = new ArrayList<User>();
    try
    {
        String str=PHPtools.GET(WEB_URL+"/listUser.php");
        JSONArray array = new JSONObject(str).getJSONArray("users");

        for(int i=0; i<array.length();i++)
        {
            JSONObject jsonObject = array.getJSONObject(i);
            ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
            User user = Const.contentValueToUser(contentValues);
            users.add(user);
            /*User user = new User ();
            user.setUserName(jsonObject.getString("userName"));
            user.setUserPassword(jsonObject.getString("userPassword"));
            */
        }
        return users;
    }
    catch (Exception e){
        e.printStackTrace();
    }
    return null;
 }
    @Override
    public List<Branch> getBranch()
    {
        List<Branch> branches = new ArrayList<Branch>();
        try
        {
            String str=PHPtools.GET(WEB_URL+"/listBranch.php");
            JSONArray array = new JSONObject(str).getJSONArray("branches");

            for(int i=0; i<array.length();i++)
            {
                JSONObject jsonObject = array.getJSONObject(i);

                Branch branch = new Branch ();
                branch.setAddress(jsonObject.getString("address"));
                branch.setNumParkingSpaces(jsonObject.getInt("numParkingSpaces"));
                branch.setBranchNumber(jsonObject.getInt("branchNumber"));

               branches.add(branch);
            }
            return branches;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<CarModel> getCarModels()
    {
        List<CarModel> carModels = new ArrayList<CarModel>();
        try
        {
            String str=PHPtools.GET(WEB_URL+"/listCarModel.php");
            JSONArray array = new JSONObject(str).getJSONArray("carModels");

            for(int i=0; i<array.length();i++)
            {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                CarModel carModel = Const.contentValueToCarModel(contentValues);
                carModels.add(carModel);
               /*
                CarModelActivity carModel = new CarModelActivity();
                carModel.setModelCode(jsonObject.getInt("modelCode"));
                carModel.setCompanyName(jsonObject.getString("companyName"));
                carModel.setModelName(jsonObject.getString("modelName"));
                carModel.setEngineCapacity(jsonObject.getDouble("engineCapacity"));
                carModel.setGearbox(CarModelActivity.Gearbox.valueOf(jsonObject.getString("gearbox")));
                carModel.setSeats(jsonObject.getInt("seats"));
                carModels.add(carModel);*/
            }
            return carModels;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> getCar()
    {
        List<Car> cars = new ArrayList<Car>();
        try
        {
            String str=PHPtools.GET(WEB_URL+"/listCar.php");
            JSONArray array = new JSONObject(str).getJSONArray("cars");

            for(int i=0; i<array.length();i++)
            {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Car car = Const.contentValueToCar(contentValues);
                cars.add(car);
            }
            return cars;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addUser(ContentValues values) throws Exception {
        try {
            String result = PHPtools.POST(WEB_URL + "/addUser.php", values);
            Long id = Long.parseLong(result);
            if (id > 0)
                SetUpdate();
            //if(!userExist(values))
                printLog("addUser:\n" + result);
            return true;
        } catch (IOException e) {
            printLog("addUser Exception:\n" + e);
            throw new Exception("This User already exist in the system");
        }
    }
@Override
    public boolean addCarModel(ContentValues values) throws Exception {
     try {
         String result = PHPtools.POST(WEB_URL + "/addCarModel.php", values);
         Long id = Long.parseLong(result);
         if (id > 0)
             SetUpdate();
         printLog("addCarModel:\n" + result);
         return true;
     } catch (IOException e) {
         printLog("addCarModel Exception:\n" + e);
         throw new Exception("This model already exist in the system");

     }
    }
    public boolean addCar(ContentValues values) throws Exception
    {
        try {
            String result = PHPtools.POST(WEB_URL + "/addCar.php", values);
            Long id = Long.parseLong(result);
            if (id > 0)
                SetUpdate();
            printLog("addCar:\n" + result);
            return true;
        } catch (IOException e) {
            printLog("addCar Exception:\n" + e);
            throw new Exception("This car already exist in the system");
        }
    }

    public boolean userExist(ContentValues user)  {
        User u = Const.contentValueToUser(user);
        if (getUser().contains(u))
            return true;
        return false;
    }

}
