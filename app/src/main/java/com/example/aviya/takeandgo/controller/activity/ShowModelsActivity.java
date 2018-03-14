package com.example.aviya.takeandgo.controller.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.aviya.takeandgo.R;
import com.example.aviya.takeandgo.controller.adapter.MyModelAdapter;
import com.example.aviya.takeandgo.model.datasource.DBmanagerFactory;
import com.example.aviya.takeandgo.model.entities.CarModel;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.util.ArrayList;
import java.util.List;

public class ShowModelsActivity extends Activity {

    List<CarModel> carModelList = new ArrayList<>();
    HorizontalInfiniteCycleViewPager pager;
    TextView modelShowWait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_models);
        pager = (HorizontalInfiniteCycleViewPager)findViewById(R.id.horizontal_cycle_models);
        modelShowWait = (TextView)findViewById(R.id.modelShowWait);
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                modelShowWait.setVisibility(View.GONE);
                pager.setAdapter(new MyModelAdapter(getBaseContext(),carModelList));
            }

            @Override
            protected Void doInBackground(Void... params) {
                carModelList = DBmanagerFactory.getManager().allCarModels();
                return null;
            }
        }.execute();


    }
}
