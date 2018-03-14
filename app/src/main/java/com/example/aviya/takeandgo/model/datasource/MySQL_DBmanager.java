package com.example.aviya.takeandgo.model.datasource;

import android.content.ContentValues;
import android.util.Log;

import com.example.aviya.takeandgo.model.backend.CompanyConst;
import com.example.aviya.takeandgo.model.backend.DB_manager;
import com.example.aviya.takeandgo.model.backend.PHPTools;
import com.example.aviya.takeandgo.model.entities.Branch;
import com.example.aviya.takeandgo.model.entities.Car;
import com.example.aviya.takeandgo.model.entities.CarModel;
import com.example.aviya.takeandgo.model.entities.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aviya on 14/12/2017.
 */

public class MySQL_DBmanager implements DB_manager {

    private final String WEB_URL = "http://aviyashu.vlab.jct.ac.il/Company/";
    private boolean updateFlag = false;

    private void SetUpdate()
    {
        updateFlag = true;
    }

    public void printLog(String message) { Log.d(this.getClass().getName(),"\n"+message); }
    public void printLog(Exception message) { Log.d(this.getClass().getName(),"Exception-->\n"+message); }

    @Override
    public void addUser(ContentValues user) throws Exception {
        try{
            String result = PHPTools.POST(WEB_URL+"/addUser.php",user);
            long id = Long.parseLong(result);
            if (id >0)
                SetUpdate();
            printLog("addUser:\n"+result);
            return;
        }
        catch (IOException e){
            printLog("addUser Exception:\n" + e);
            throw new Exception("This user name already exist in the system");
        }
    }

    @Override
    public void addClient(ContentValues client) throws Exception {
        try{
            String result = PHPTools.POST(WEB_URL+"/addClient.php",client);
            long id = Long.parseLong(result);
            if (id >0)
                SetUpdate();
            printLog("addClient:\n"+result);
            return;
        }
        catch (IOException e){
            printLog("addClient Exception:\n" + e);
            throw new Exception("This client already exist in the system");
        }
    }

    @Override
    public boolean existUser(ContentValues contentValues) {
        User user = CompanyConst.ContentValuesToUser(contentValues);
        boolean flag =false;
        for (User u: allUsers()) {
            if (u.getUserName().equals(user.getUserName()) && u.getPassword().equals(user.getPassword())){
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public String addCarModel(ContentValues carModel) throws Exception {
        try{
            String result = PHPTools.POST(WEB_URL+"/addModel.php",carModel);
            long id = Long.parseLong(result);
            if (id >0)
                SetUpdate();
            printLog("addModel:\n"+result);
            return result;
        }
        catch (IOException e){
            printLog("addModel Exception:\n" + e);
            throw new Exception("This model already exist in the system");
        }
    }

    @Override
    public String addCar(ContentValues car) throws Exception {
        try{
            String result = PHPTools.POST(WEB_URL+"/addCar.php",car);
            long id = Long.parseLong(result);
            if (id >0)
                SetUpdate();
            printLog("addCar:\n"+result);
            return result;
        }
        catch (IOException e){
            printLog("addCar Exception:\n" + e);
            throw new Exception("This car already exist in the system");
        }
    }

    @Override
    public List<User> allUsers() {
        List<User> list = new ArrayList<User>();
        try{
            String str = PHPTools.GET(WEB_URL+"/users.php");
            JSONArray array = new JSONObject(str).getJSONArray("users");
            for (int i=0; i< array.length(); i++){
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPTools.JsonToContentValues(jsonObject);
                User user = CompanyConst.ContentValuesToUser(contentValues);

                list.add(user);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
       return list;
    }

    @Override
    public List<CarModel> allCarModels() {
        List<CarModel> models = new ArrayList<CarModel>();
        try{
            String str = PHPTools.GET(WEB_URL+"models.php");
            JSONArray array = new JSONObject(str).getJSONArray("models");
            for (int i=0; i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPTools.JsonToContentValues(jsonObject);
                CarModel carModel = CompanyConst.ContentValuesToCarModel(contentValues);

                models.add(carModel);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return models;
    }

    @Override
    public List<Car> allCars() {
        List<Car> cars = new ArrayList<Car>();
        try{
            String string = PHPTools.GET(WEB_URL+"/cars.php");
            JSONArray jsonArray = new JSONObject(string).getJSONArray("cars");
            for (int i=0; i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ContentValues contentValues = PHPTools.JsonToContentValues(jsonObject);
                Car car = CompanyConst.ContentValuesToCar(contentValues);

                cars.add(car);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return cars;
    }

    @Override
    public List<Branch> allBranches() {
        List<Branch> list = new ArrayList<Branch>();
        try{
            String string = PHPTools.GET(WEB_URL+"/branches.php");
            JSONArray array = new JSONObject(string).getJSONArray("branches");
            for (int i=0; i< array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPTools.JsonToContentValues(jsonObject);
                Branch branch = CompanyConst.ContentValuesToBranch(contentValues);

                list.add(branch);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }
}
