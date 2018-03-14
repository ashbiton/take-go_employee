package com.example.aviya.takeandgo.controller.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aviya.takeandgo.R;
import com.example.aviya.takeandgo.model.backend.CompanyConst;
import com.example.aviya.takeandgo.model.datasource.DBmanagerFactory;
import com.example.aviya.takeandgo.model.entities.CarModel;
import com.example.aviya.takeandgo.model.entities.GEAR;

public class AddCarModelActivity extends Activity {
    private EditText editTextCarCompany;
    private EditText editTextCarModelModelName;
    private EditText editTextCarModelModelCode;
    private EditText editTextEngineCapacity;
    private EditText editTextAcceleration;
    private EditText editTextHorsePower;
    private EditText editTextSeats;
    private EditText editTextDoors;
    private EditText editTextTrunkVolume;
    private SeekBar seekbarMaxSpeed;
    private Spinner spinnerGearBox;
    private CheckBox checkboxIsTurbo;
    private CheckBox checkboxIsConvertible;
    private EditText editTextKilometersPerTank;
    private CheckBox checkboxCodeNeeded;
    private EditText editTextCodeNum;
    private ImageButton imageButtonAddCarModel;
    private CarModel carModel;

    private void findViews() {
        editTextCarCompany = (EditText)findViewById( R.id.editTextCarCompany );
        editTextCarModelModelName = (EditText)findViewById( R.id.editTextCarModelModelName );
        editTextCarModelModelCode = (EditText)findViewById( R.id.editTextCarModelModelCode );
        editTextEngineCapacity = (EditText)findViewById( R.id.editTextEngineCapacity );
        editTextAcceleration = (EditText)findViewById( R.id.editTextAcceleration );
        editTextHorsePower = (EditText)findViewById( R.id.editTextHorsePower );
        editTextSeats = (EditText)findViewById( R.id.editTextSeats );
        editTextDoors = (EditText)findViewById( R.id.editTextDoors );
        editTextTrunkVolume = (EditText)findViewById( R.id.editTextTrunkVolume );
        seekbarMaxSpeed = (SeekBar)findViewById( R.id.seekbarMaxSpeed );
        spinnerGearBox = (Spinner)findViewById( R.id.spinnerGearBox );
        checkboxIsTurbo = (CheckBox)findViewById( R.id.checkboxIsTurbo );
        checkboxIsConvertible = (CheckBox)findViewById( R.id.checkboxIsConvertible );
        editTextKilometersPerTank = (EditText)findViewById( R.id.editTextKilometersPerTank );
        checkboxCodeNeeded = (CheckBox)findViewById( R.id.checkboxCodeNeeded );
        editTextCodeNum = (EditText)findViewById( R.id.editTextCodeNum );
        imageButtonAddCarModel = (ImageButton)findViewById( R.id.imageButtonAddCarModel );
        carModel = new CarModel();

        codeVisibility();

        spinnerGearBox.setAdapter(new ArrayAdapter<GEAR>(this,R.layout.support_simple_spinner_dropdown_item,GEAR.values()));
        spinnerGearBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                carModel.setGearBox(GEAR.valueOf(spinnerGearBox.getItemAtPosition(position).toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                carModel.setGearBox(GEAR.valueOf(spinnerGearBox.getItemAtPosition(0).toString()));
            }
        });

        imageButtonAddCarModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getCarModelValues();
            }
        });
    }

    private void getCarModelValues() {
        try{
        carModel.setModelCode(editTextCarModelModelCode.getText().toString());
        carModel.setModelName(editTextCarModelModelName.getText().toString());
        carModel.setKilometersPerTank(Double.parseDouble(editTextKilometersPerTank.getText().toString()));
        carModel.setConvertible(checkboxIsConvertible.isChecked());
        carModel.setHorsePower(Double.parseDouble(editTextHorsePower.getText().toString()));
        carModel.setCodeRequired(checkboxCodeNeeded.isChecked());
        if(carModel.isCodeRequired()){carModel.setCodeNumber(Integer.parseInt(editTextCodeNum.getText().toString()));}
        carModel.setEngineCapacity(Double.parseDouble(editTextEngineCapacity.getText().toString()));
        carModel.setBuildInTurbo(checkboxIsTurbo.isChecked());
        carModel.setTrunkVolume(Double.parseDouble(editTextTrunkVolume.getText().toString()));
        carModel.setAcceleration(Double.parseDouble(editTextAcceleration.getText().toString()));
        carModel.setCarCompany(editTextCarCompany.getText().toString());
        carModel.setMaxSpeed(seekbarMaxSpeed.getProgress());
        carModel.setSeats(Integer.parseInt(editTextSeats.getText().toString()));
        carModel.setDoors(Integer.parseInt(editTextDoors.getText().toString()));

        final ContentValues cv = CompanyConst.CarModelToContentValues(carModel);
            new AsyncTask<Void, Boolean, Boolean>() {
                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    if(aBoolean){
                        Toast.makeText(getApplicationContext(), R.string.add_model_success,Toast.LENGTH_SHORT);
                    }
                }

                @Override
                protected Boolean doInBackground(Void... params) {
                    try {
                        DBmanagerFactory.getManager().addCarModel(cv);
                        return true;
                    }
                    catch (final Exception e) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {Toast.makeText(AddCarModelActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();}});
                        return false;
                    }
                }
            }.execute();
        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG);
        }
    }

    private void codeVisibility() {
        editTextCodeNum.setVisibility(View.INVISIBLE);
        checkboxCodeNeeded.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    editTextCodeNum.setVisibility(View.VISIBLE);
                else
                    editTextCodeNum.setVisibility(View.INVISIBLE);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_car_model);
            findViews();

    }
}

