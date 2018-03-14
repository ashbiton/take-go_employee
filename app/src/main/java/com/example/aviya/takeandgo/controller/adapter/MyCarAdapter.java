package com.example.aviya.takeandgo.controller.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aviya.takeandgo.R;
import com.example.aviya.takeandgo.model.entities.Branch;
import com.example.aviya.takeandgo.model.entities.Car;
import com.example.aviya.takeandgo.model.entities.CarModel;

import java.util.List;

/**
 * Created by aviya on 30/11/2017.
 */

public class MyCarAdapter extends PagerAdapter {
    List<Car> carList;
    List<CarModel> modelList;
    List<Branch> branchList;
    Context context;
    LayoutInflater mLayoutInflater;


    public MyCarAdapter(List<Car> carList, List<CarModel> modelList, List<Branch> branchList, Context context) {
        this.carList = carList;
        this.modelList = modelList;
        this.branchList = branchList;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return carList.size();
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
        View view = mLayoutInflater.inflate(R.layout.car_item,container,false);
        TextView carPresentCarNumber = (TextView)view.findViewById( R.id.car_present_car_number );
        TextView carPresentCarCompany = (TextView)view.findViewById( R.id.car_present_car_company );
        TextView carPresentCarModel = (TextView)view.findViewById( R.id.car_present_car_model );
        TextView carPresentCarKilometers = (TextView)view.findViewById( R.id.car_present_car_kilometers );
        TextView carPresentCarHouse = (TextView)view.findViewById( R.id.car_present_car_house );
        TextView carPresentCarStreet = (TextView)view.findViewById( R.id.car_present_car_street );
        TextView carPresentCarCity = (TextView)view.findViewById( R.id.car_present_car_city );

        Car car = carList.get(position);

        view.setBackgroundColor(car.getCarColor());

        carPresentCarNumber.setText(car.getCarNumber());
        carPresentCarKilometers.setText(String.valueOf(car.getKilometers()));

        for (Branch b:branchList) {
            if (b.getBranchNumber() == car.getHomeBranch())
            {
                carPresentCarCity.setText(b.getCity());
                carPresentCarStreet.setText(b.getStreet());
                carPresentCarHouse.setText(b.getHouseNumber()+"");
                break;
            }
        }

        for (CarModel cm:modelList){
            if(cm.getModelCode().equals(car.getModelCode()))
            {
                carPresentCarCompany.setText(cm.getCarCompany());
                carPresentCarModel.setText(cm.getModelName());
                break;
            }
        }

        container.addView(view);
        return view;
    }
}
