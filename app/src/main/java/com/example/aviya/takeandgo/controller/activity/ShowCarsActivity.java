package com.example.aviya.takeandgo.controller.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.aviya.takeandgo.R;
import com.example.aviya.takeandgo.controller.adapter.MyCarAdapter;
import com.example.aviya.takeandgo.model.datasource.DBmanagerFactory;
import com.example.aviya.takeandgo.model.entities.Branch;
import com.example.aviya.takeandgo.model.entities.Car;
import com.example.aviya.takeandgo.model.entities.CarModel;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.util.ArrayList;
import java.util.List;

public class ShowCarsActivity extends Activity {

    List<Car> carList = new ArrayList<>();
    List<CarModel> modelList = new ArrayList<>();
    List<Branch> branchList = new ArrayList<>();
    HorizontalInfiniteCycleViewPager pager;
    TextView carShowWait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cars);
        pager = (HorizontalInfiniteCycleViewPager) findViewById(R.id.horizontal_cycle_cars);
        carShowWait = (TextView) findViewById(R.id.carShowWait);
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                carList = DBmanagerFactory.getManager().allCars();
                branchList = DBmanagerFactory.getManager().allBranches();
                modelList = DBmanagerFactory.getManager().allCarModels();
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                carShowWait.setVisibility(View.GONE);
                MyCarAdapter adapter = new MyCarAdapter(carList,modelList,branchList,getBaseContext());
                pager.setAdapter(adapter);
            }
        }.execute();
    }
}
