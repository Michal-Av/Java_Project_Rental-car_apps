package com.example.talia.android5778_5956_6419_02.models.datasource;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.widget.Toast;

import com.example.talia.android5778_5956_6419_02.controllers.FragmentBranches;
import com.example.talia.android5778_5956_6419_02.models.backend.Const;
import com.example.talia.android5778_5956_6419_02.models.backend.IDB_Manager;
import com.example.talia.android5778_5956_6419_02.models.entities.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.talia.android5778_5956_6419_02.models.entities.Order.ORDER_NUM;


/**
 * Created by michalus.av on 22/01/2018.
 */

public class MySQL_DBManager implements IDB_Manager {
    private String WEB_URL="http://tsameret.vlab.jct.ac.il/";
    static List<Car> carList;
    static List<Branch> branchList;
    static List<Customer> customerList;
    static List<Order> reservationList;
    static List<User> userCustomerList;
    protected Context context;

    @Override
    public boolean isExists(ContentValues user) {
        User u = Const.contentValueToUser(user);
            try{
            List<User> userList=getUsers();
            if(userList.contains(u))
                return true;
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return  false;
        }

    }

    public boolean existCustomer(int id){
        for (Customer customer:getCustomer())
            if(customer.getId() == id)
               return false;
           return true;
    }
    public Customer findCustomer(int id){
        for (Customer customer:getCustomer())
            if(customer.getId() == id)
                return customer;
        return null;
    }

    public boolean existCustomer(String firstName, String password){
        for (Customer customer:getCustomer())
            if((customer.getFirstName()).equals(firstName) && (customer.getPassword()).equals(password))
                return true;
        return false;
    }

    private Order openReservationForCar(int carNumber){
        for (Order reservation:getOrders())
            if(reservation.getNumCar()== (carNumber) && !reservation.getOpenOrder())
                return reservation;
        return null;
    }

    public Order openReservationForCustomer(int id){
        for (Order reservation:getOrders())
            if(reservation.getNumCustomer() == id && reservation.getOpenOrder())
                return reservation;
        return null;
    }

    /**
     * look for a specific reservation in the database , given a unique identifier
     * @param orderNumber unique identifier for reservation
     * @return the reservation that matches the argument or null
     */
    public Order findOrder(int orderNumber){
        for (Order order : getOrders())
            if(order.getOrderNum() == orderNumber)
                return order;
        return null;
    }
    public Order findOrderById(int id){
        for (Order order : getOrders())
            if(order.getNumCustomer() == id)
                return order;
        return null;
    }

    @Override
    public String addUser(ContentValues user) {
        try {
            String result = PHPtools.POST(WEB_URL + "addUser.php", user);
            return result;
        } catch (IOException e) {
            System.out.println("addUser Exception:\n" + e);
            return null;
        }
    }
    @Override
    public String addCustomer(ContentValues user) {
        try {
            String result = PHPtools.POST(WEB_URL + "addCustomer.php", user);
            return result;
        } catch (IOException e) {
            System.out.println("addCustomer Exception:\n" + e);
            return null;
        }
    }
    @Override
    public Double updateCar(ContentValues car, int k) throws Exception {
        Car c = Const.contentValueToCar(car);
        c.setMileage(k);
        String result="";
        try {
            result = PHPtools.POST(WEB_URL + "update_car.php",Const.CarToContentValue(c) );
        } catch (IOException e) {
            System.out.println("upDate Exception:\n" + e);
        }
        return Double.parseDouble(result);
    }

    @Override
    public List<User> getUsers() {
        List<User> result = new ArrayList<User>();
        try {
            String str = PHPtools.GET(WEB_URL + "listUser.php");
            JSONArray array = new JSONObject(str).getJSONArray("users");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                User user = Const.contentValueToUser(contentValues);
                result.add(user);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public List<Branch> getBranches() {
        List<Branch> result = new ArrayList<Branch>();
        try {
            String str = PHPtools.GET(WEB_URL + "listBranch.php");
            JSONArray array = new JSONObject(str).getJSONArray("branches");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Branch branch  = Const.contentValueToBranch(contentValues);
                result.add(branch);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Car> getCars() {
        List<Car> result = new ArrayList<Car>();
        try {
            String str = PHPtools.GET(WEB_URL + "listCar.php");
            JSONArray array = new JSONObject(str).getJSONArray("cars");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Car car = Const.contentValueToCar(contentValues);
                result.add(car);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Order> getOrders() {
        List<Order> result = new ArrayList<Order>();
        try {
            String str = PHPtools.GET(WEB_URL + "listOrder.php");
            JSONArray array = new JSONObject(str).getJSONArray("orders");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Order order = Const.contentValueToOrder(contentValues);
                result.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Customer> getCustomer() {
        List<Customer> result = new ArrayList<Customer>();
        try {
            String str = PHPtools.GET(WEB_URL + "listCustomer.php");
            JSONArray array = new JSONObject(str).getJSONArray("customers");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Customer customer = Const.contentValueToCustomer(contentValues);
                result.add(customer);
            }
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> allFreeCars() {

        List<Car> result = new ArrayList<Car>();
        try
        {
            List<Car> cars=getCars();
            List<Order> orders=getOrders();
            for (Car c:cars)
            {
                boolean haveNoOrder=true,allOrdersClose=true;
                for (Order o:orders)
                    if(c.getNumCar()== o.getNumCar()) {
                        haveNoOrder = false;
                        if (o.getOpenOrder()) {
                            allOrdersClose=false;
                        }
                    }
                if(haveNoOrder || allOrdersClose )
                    result.add(c);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Car> freeCarsForBranch(ContentValues branch) {
        List<Car> result = new ArrayList<Car>();
        Branch b = Const.contentValueToBranch(branch);
        List<Car> freeCars = allFreeCars();
        try
        {
            for (Car c:freeCars)
                if(c.getNumBranch()==b.getBranchNumber())
                    result.add(c);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

   /* @Override
    public List<CarModel> getCarModels() {
        List<CarModel> result = new ArrayList<CarModel>();
        try {
            String str = PHPtools.GET(WEB_URL + "show_carModels.php");
            JSONArray array = new JSONObject(str).getJSONArray("carModels");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                CarModel carModel = Constants.ContentValuesToCarModel(contentValues);
                result.add(carModel);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }*/

    @Override
    public List<Order> ordersNotClosed() {
        List<Order> result = new ArrayList<Order>();
        List<Order> orders = getOrders();
        try
        {
            for (Order o:orders)
                if(o.getOpenOrder())
                    result.add(o);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String addOrder(ContentValues order) {
        try {
            String result = PHPtools.POST(WEB_URL + "addOrder.php", order);
            return result;
        } catch (IOException e) {
            System.out.println("addOrder Exception:\n" + e);
            return null;
        }
    }

    public int addReservation(Order reservation) {

        if(openReservationForCustomer(reservation.getNumCustomer()) != null)
           return 0;
            //throw new Exception("you already have an open reservation");
           // Toast.makeText(context,"you already have an open reservation",Toast.LENGTH_LONG).show();
        reservation.setStartRent(Calendar.getInstance().getTime());
        reservation.setEndRent(Calendar.getInstance().getTime()); // assigned so it can be converted to content values
        reservation.setOpenOrder(true);
        int reservationNumber = 0;
        ContentValues cv = Const.OrderToContentValue(reservation);
        try{
            String str = PHPtools.POST(WEB_URL+"/addOrder.php",cv);
            //reservationNumber = Integer.parseInt(str);
            reservationNumber=ORDER_NUM++;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        reservation.setOrderNum(reservationNumber);
        reservationList.add(reservation);
        return reservationNumber;
    }

    @Override
    public Double closeOrder(int orderNumber , int kilometers , Double addedFuel) throws Exception {

        Order order=new Order();
        List<Order> orderList=getOrders();
        List<Car> carList=getCars();
        for (Order o:orderList) {
            if (o.getOrderNum() == orderNumber)
                order = o;
        }
        if(order == null)
            throw new Exception("reservation not found in the system");
        order.setOpenOrder(false);
        order.setEndMileage(order.getStartMileage() + kilometers);
        order.setEndRent(Calendar.getInstance().getTime());
        if(addedFuel == 0)
            order.setFuelFilling(false);
        else
            order.setFuelFilling(true);
        order.setFuel(addedFuel);

        for (Car c:carList)
        {
            if(c.getNumCar()==order.getNumCar())
                updateCar(Const.CarToContentValue(c) ,kilometers);
        }
        order.setPayment(calculatePayment(order));

        try {
            String result = PHPtools.POST(WEB_URL + "updateOrder.php",Const.OrderToContentValue(order) );

        } catch (IOException e) {
            System.out.println("upDate Exception:\n" + e);
        }

        return order.getPayment();
    }


    private Double calculatePayment(Order res) {
        Double finalPayment = 50.0 ;
        long daysOfRent = daysBetween(res.getStartRent(),res.getEndRent());
        finalPayment += daysOfRent *  50 ;       //50 dollars for day rent
        return finalPayment;
    }

    private long daysBetween(Date startDate, Date endDate) {
        Calendar sDate = getDatePart(startDate);
        Calendar eDate = getDatePart(endDate);

        long daysBetween = 0;
        while (sDate.before(eDate)) {
            sDate.add(Calendar.DAY_OF_MONTH, 1); // add another day
            daysBetween++;                       // singles another day difference
        }
        return daysBetween;
    }

    private Calendar getDatePart(Date date) {
        Calendar cal = Calendar.getInstance();       // get calendar instance
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
        cal.set(Calendar.MINUTE, 0);                 // set minute in hour
        cal.set(Calendar.SECOND, 0);                 // set second in minute
        cal.set(Calendar.MILLISECOND, 0);            // set millisecond in second
        return cal;
    }

    private boolean tenOrLessSecondsDiff(Date now , Date date){
        Calendar calNow = getDatePart(now);
        if(calNow.equals(getDatePart(date))){       // check if the date as the same day, month and year
            calNow.setTime(now);                    // "setting back" the time properties for now
            calNow.add(Calendar.SECOND,-10);        // find the time 10 seconds ago
            if(date.after(calNow.getTime()) ||      // if date is in the last 10 seconds
                    date.equals(calNow.getTime()))  // or date is exactly 10 seconds ago
                return true;
        }
        return false;
    }
    @Override
    public boolean checkClosedOrder() {

        Date now = Calendar.getInstance().getTime();
        List<Order> orderList=getOrders();
        for (Order order:orderList){
            if (tenOrLessSecondsDiff(now,order.getEndRent()) && !order.getOpenOrder())
                return true;
        }

        return false;
    }

    public void initializeLists(){
        carList = new ArrayList<>();
        branchList = new ArrayList<>();
        customerList = new ArrayList<>();
        reservationList = new ArrayList<>();
        userCustomerList = new ArrayList<>();
        getUsers();
        getCustomer();
        getBranches();
        getCars();
        getOrders();
    }


}
