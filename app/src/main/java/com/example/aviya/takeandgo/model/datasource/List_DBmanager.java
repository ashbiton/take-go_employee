package com.example.aviya.takeandgo.model.datasource;

import android.content.ContentValues;
import android.graphics.Color;

import com.example.aviya.takeandgo.model.backend.CompanyConst;
import com.example.aviya.takeandgo.model.backend.DB_manager;
import com.example.aviya.takeandgo.model.entities.Branch;
import com.example.aviya.takeandgo.model.entities.Car;
import com.example.aviya.takeandgo.model.entities.CarModel;
import com.example.aviya.takeandgo.model.entities.Client;
import com.example.aviya.takeandgo.model.entities.GEAR;
import com.example.aviya.takeandgo.model.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aviya on 01/12/2017.
 */

public class List_DBmanager implements DB_manager {
    /**
     * Created by aviya on 19/11/2017.
     */
    static List<User> userList;
    static List<Car> carList;
    static List<CarModel> carModelList;
    static List<Branch> branchList;
    static List<Client> clientList;

    static {
        userList = new ArrayList<>();
        carList = new ArrayList<>();
        carModelList = new ArrayList<>();
        branchList = new ArrayList<>();
        clientList = new ArrayList<>();

        userList.add(new User("aviya","313416067"));
        userList.add(new User("daniel","fdm345gfkm"));
        userList.add(new User("moriya","moriyali1234"));

        carList.add(new Car(1234,"123rt",350,"123456789", Color.BLUE));
        carList.add(new Car(2235,"156nj",1500,"38965789", Color.MAGENTA));
        carList.add(new Car(1234,"14yy56",700,"328620789", Color.CYAN));

        branchList.add(new Branch(33,"Kedumim","Hagit",40,1234));
        branchList.add(new Branch(15,"Jerusalem","Bracha",200,2235));
        branchList.add(new Branch(46,"Netanya","Shomron",120,546));

        carModelList.add(new CarModel("1234rt","Honda","Slash",2300, GEAR.automatic,5,4,30,50,false,300,false,1200,3200,false,-1));
        carModelList.add(new CarModel("14yy56","Toyota","Corola",1500, GEAR.manual,9,4,50,30,true,250,false,1200,4200,true,2340));
        carModelList.add(new CarModel("156nj","Siat","Ibiza",2000, GEAR.automatic,5,2,60,60,true,350,true,3000,1500,true,8852));

    }

    @Override
    public void addUser(ContentValues contentValues) throws Exception {
        User user = CompanyConst.ContentValuesToUser(contentValues);
        for (User u: userList) {
            if (u.getUserName().equals(user.getUserName())){
                throw new Exception("This user name already exist in the system");
            }
        }
        userList.add(user);
    }

    @Override
    public void addClient(ContentValues contentValues) throws Exception {
        Client client = CompanyConst.ContentValuesToClient(contentValues);
        for (Client c: clientList) {
            if (c.get_id() == client.get_id())
                throw new Exception("This client already exist in the system");
        }
        clientList.add(client);
    }

    @Override
    public boolean existUser(ContentValues contentValues) {
        User user = CompanyConst.ContentValuesToUser(contentValues);
        boolean flag =false;
        for (User u: userList) {
            if (u.getUserName().equals(user.getUserName()) && u.getPassword().equals(user.getPassword())){
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public String addCarModel(ContentValues contentValues) throws Exception {
        CarModel carModel = CompanyConst.ContentValuesToCarModel(contentValues);
        for (CarModel cm: carModelList) {
            if (cm.getModelCode().equals(carModel.getModelCode()))
                throw new Exception("This car model already exist in the system");
        }
        carModelList.add(carModel);
        return carModel.getModelCode();
    }

    @Override
    public String addCar(ContentValues contentValues) throws Exception {
        Car car = CompanyConst.ContentValuesToCar(contentValues);
        for (Car c:carList) {
            if (c.getCarNumber().equals(car.getCarNumber()))
                throw new Exception("This car already exist in the system");
        }
        carList.add(car);
        return car.getCarNumber();
    }

    @Override
    public List<User> allUsers() {
        return userList;
    }

    @Override
    public List<CarModel> allCarModels() {
        return carModelList;
    }

    @Override
    public List<Car> allCars() {
        return carList;
    }

    @Override
    public List<Branch> allBranches() {
        return branchList;
    }
}
