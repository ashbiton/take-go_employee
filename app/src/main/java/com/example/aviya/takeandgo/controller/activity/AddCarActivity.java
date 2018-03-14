package com.example.aviya.takeandgo.controller.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aviya.takeandgo.R;
import com.example.aviya.takeandgo.model.backend.CompanyChecks;
import com.example.aviya.takeandgo.model.backend.CompanyConst;
import com.example.aviya.takeandgo.model.datasource.DBmanagerFactory;
import com.example.aviya.takeandgo.model.entities.Branch;
import com.example.aviya.takeandgo.model.entities.Car;
import com.example.aviya.takeandgo.model.entities.CarModel;

import java.util.ArrayList;
import java.util.List;

public class AddCarActivity extends Activity implements View.OnClickListener {
    Car car = new Car();
    private Spinner carModelSpinner;
    private EditText editTextFirstSecCarNum;
    private EditText editTextSecondSecCarNum;
    private EditText editTextThirdSecCarNum;
    private EditText editTextCarKilometers;
    private Spinner carBranchSpinner;
    private Button pickColorBtn;
    private Button addCarBtn;
    private List<CarModel> modelList;
    private List<Branch> branchList;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-12-08 04:11:27 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        carModelSpinner = (Spinner)findViewById( R.id.carModelSpinner );
        editTextFirstSecCarNum = (EditText)findViewById( R.id.editTextFirstSecCarNum );
        editTextSecondSecCarNum = (EditText)findViewById( R.id.editTextSecondSecCarNum );
        editTextThirdSecCarNum = (EditText)findViewById( R.id.editTextThirdSecCarNum );
        editTextCarKilometers = (EditText)findViewById( R.id.editTextCarKilometers );
        carBranchSpinner = (Spinner)findViewById( R.id.carBranchSpinner );
        pickColorBtn = (Button)findViewById( R.id.pickColorBtn );
        addCarBtn = (Button)findViewById(R.id.addCarBtn);

        pickColorBtn.setOnClickListener( this );
        addCarBtn.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if ( v == addCarBtn) {
            addCar();
        }
        else if (v == pickColorBtn) {
            pickColor();

        }
    }

    private void pickColor() {
        final int [] colors = new int[]{Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW,Color.CYAN};
        final int [] colors1 = new int[]{Color.BLACK,Color.GRAY,Color.MAGENTA,Color.WHITE,Color.LTGRAY};
        colorLayoutInit(R.id.colorLayout,colors);
        colorLayoutInit(R.id.colorLayout1,colors1);
        LinearLayout colorPickerLayout = (LinearLayout)findViewById(R.id.colorPickerLayout);
        colorPickerLayout.setVisibility(View.VISIBLE);
    }

    private void addCar() {
        try
        {
            String car_number = editTextFirstSecCarNum.getText().toString();
            car_number += editTextSecondSecCarNum.getText().toString() + editTextThirdSecCarNum.getText().toString();
            CompanyChecks.Car.checkCarNumber(car_number);
            double kilometers = Double.parseDouble(editTextCarKilometers.getText().toString());
            car.setCarNumber(car_number);
            car.setKilometers(kilometers);
            final ContentValues cv = CompanyConst.CarToContentValues(car);

            new AsyncTask<Void, Boolean, Boolean>() {
                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    if(aBoolean)
                        Toast.makeText(getApplicationContext(),R.string.add_car_success,Toast.LENGTH_LONG).show();
                }

                @Override
                protected Boolean doInBackground(Void... params) {
                    try{
                        DBmanagerFactory.getManager().addCar(cv);
                        return true;
                    }
                    catch (final Exception e){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {Toast.makeText(AddCarActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();}});
                        return false;
                    }
                }
            }.execute();
        }
        catch (Exception c)
        {
            Toast.makeText(this,c.getMessage(),Toast.LENGTH_LONG).show();
        }
    }


    private void initSpinners() {
        List<String> models = new ArrayList<>();
        for (CarModel m:modelList) {
            models.add(m.getModelCode());
        }
        List<Integer> branches = new ArrayList<>();
        for (Branch b:branchList){
            branches.add(b.getBranchNumber());
        }

        ArrayAdapter<String> modelAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,models);
        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carModelSpinner.setAdapter(modelAdapter);
        ArrayAdapter<Integer> branchAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item,branches);
        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carBranchSpinner.setAdapter(branchAdapter);

        carModelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                car.setModelCode(carModelSpinner.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                car.setModelCode(carModelSpinner.getItemAtPosition(0).toString());
            }
        });
        carBranchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                car.setHomeBranch(Integer.parseInt(carBranchSpinner.getItemAtPosition(position).toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                car.setHomeBranch(Integer.parseInt(carBranchSpinner.getItemAtPosition(0).toString()));
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        new AsyncTask<Void,Void,Void>() {
            @Override
            protected void onPostExecute(Void aVoid) {
                initSpinners();
            }

            @Override
            protected Void doInBackground(Void... params) {
                branchList = DBmanagerFactory.getManager().allBranches();
                modelList = DBmanagerFactory.getManager().allCarModels();
                return null;
            }
        }.execute();

        findViews();
    }

    private void colorLayoutInit(int id,int [] colors){
        LinearLayout linearLayout = (LinearLayout) findViewById(id);
        int i=0;
        for (; i<linearLayout.getChildCount(); i++)
        {
            View v = linearLayout.getChildAt(i);
            v.setBackgroundColor(colors[i]);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Drawable back = v.getBackground();
                    int color;
                    if(back instanceof ColorDrawable)
                    { color = ((ColorDrawable) back).getColor();
                      pickColorBtn.setTextColor(color);
                      car.setCarColor(color);}
                }
            });
        }
    }
}
