package com.example.aviya.takeandgo.model.backend;

import android.content.ContentValues;

import com.example.aviya.takeandgo.model.entities.*;

import java.util.List;

/**
 * Created by aviya on 19/11/2017.
 */

public interface DB_manager {
    void addUser(ContentValues user) throws Exception;
    void addClient(ContentValues client) throws Exception;
    boolean existUser(ContentValues user);
    String addCarModel (ContentValues carModel) throws Exception;
    String addCar(ContentValues car) throws Exception;
    List<User> allUsers();
    List<CarModel> allCarModels ();
    List<Car> allCars();
    List<Branch> allBranches();
}
