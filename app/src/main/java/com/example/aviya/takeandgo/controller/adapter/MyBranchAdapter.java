package com.example.aviya.takeandgo.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.aviya.takeandgo.R;
import com.example.aviya.takeandgo.model.entities.Branch;

import java.util.List;

/**
 * Created by aviya on 04/12/2017.
 */

public class MyBranchAdapter extends BaseAdapter {

    List<Branch> list;
    LayoutInflater mlayoutInflater;

    private TextView branchPresentBranchNumber;
    private TextView branchPresentHouse;
    private TextView branchPresentStreet;
    private TextView branchPresentCity;
    private TextView branchPresentParking;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-12-05 00:31:52 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View view) {
        branchPresentBranchNumber = (TextView)view.findViewById( R.id.branch_present_branch_number );
        branchPresentHouse = (TextView)view.findViewById( R.id.branch_present_house );
        branchPresentStreet = (TextView)view.findViewById( R.id.branch_present_street );
        branchPresentCity = (TextView)view.findViewById( R.id.branch_present_city );
        branchPresentParking = (TextView)view.findViewById( R.id.branch_present_parking );
    }


    public MyBranchAdapter(Context context, List<Branch> lst)
    {
        list = lst;
        mlayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mlayoutInflater.inflate(R.layout.branch_item,null);
        findViews(view);
        Branch branch = list.get(position);
        branchPresentBranchNumber.setText(String.valueOf(branch.getBranchNumber()));
        branchPresentCity.setText(branch.getCity());
        branchPresentHouse.setText(String.valueOf(branch.getHouseNumber()));
        branchPresentParking.setText(String.valueOf(branch.getParkingSpaces()));
        branchPresentStreet.setText(branch.getStreet());
        return view;
    }
}
