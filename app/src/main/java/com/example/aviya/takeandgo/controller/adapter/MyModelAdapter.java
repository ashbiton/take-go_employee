package com.example.aviya.takeandgo.controller.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aviya.takeandgo.R;
import com.example.aviya.takeandgo.model.entities.CarModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aviya on 04/12/2017.
 */

public class MyModelAdapter extends PagerAdapter {
    List<CarModel> list= new ArrayList<>();
    Context context;
    LayoutInflater mLayoutInflater;

    private TextView modelPresentCompany;
    private TextView modelPresentModelName;
    private TextView modelPresentModelCode;
    private TextView modelPresentKilometers;
    private TextView modelPresentCapacity;
    private TextView modelPresentHorsePower;
    private TextView modelPresentTrunk;
    private TextView modelPresentSeats;
    private TextView modelPresentGear;
    private TextView modelPresentSpeed;
    private TextView modelPresentTurbo;
    private TextView modelPresentCode;

    private void findViews(View view) {
        modelPresentCompany = (TextView)view.findViewById( R.id.model_present_company );
        modelPresentModelName = (TextView)view.findViewById( R.id.model_present_model_name );
        modelPresentModelCode = (TextView)view.findViewById( R.id.model_present_model_code );
        modelPresentKilometers = (TextView)view.findViewById( R.id.model_present_kilometers );
        modelPresentCapacity = (TextView)view.findViewById( R.id.model_present_capacity );
        modelPresentHorsePower = (TextView)view.findViewById( R.id.model_present_horse_power );
        modelPresentTrunk = (TextView)view.findViewById( R.id.model_present_trunk );
        modelPresentSeats = (TextView)view.findViewById( R.id.model_present_seats );
        modelPresentGear = (TextView)view.findViewById( R.id.model_present_gear );
        modelPresentSpeed = (TextView)view.findViewById( R.id.model_present_speed );
        modelPresentTurbo = (TextView)view.findViewById( R.id.model_present_turbo );
        modelPresentCode = (TextView)view.findViewById( R.id.model_present_code );
    }


    public MyModelAdapter(Context context ,List<CarModel> lst){
        this.context = context;
        list = lst;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mLayoutInflater.inflate(R.layout.model_item,container,false);
        CarModel carModel = list.get(position);
        findViews(view);
        modelPresentCompany.setText(carModel.getCarCompany());
        modelPresentModelName.setText(carModel.getModelName());
        modelPresentModelCode.setText(carModel.getModelCode());
        modelPresentCapacity.setText(String.valueOf(carModel.getEngineCapacity()));
        if(carModel.isCodeRequired())
            modelPresentCode.setText(String.valueOf(carModel.getCodeNumber()));
        modelPresentGear.setText(carModel.getGearBox().toString());
        modelPresentHorsePower.setText(String.valueOf(carModel.getHorsePower()));
        modelPresentKilometers.setText(String.valueOf(carModel.getKilometersPerTank()));
        modelPresentSeats.setText(String.valueOf(carModel.getSeats()));
        modelPresentSpeed.setText(String.valueOf(carModel.getMaxSpeed()));
        modelPresentTrunk.setText(String.valueOf(carModel.getTrunkVolume()));
        if (carModel.isBuildInTurbo())
            modelPresentTurbo.setText("included");
        container.addView(view);
        return view;
    }
}
