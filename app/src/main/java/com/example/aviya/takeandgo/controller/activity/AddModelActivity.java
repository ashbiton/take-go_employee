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
import android.widget.TextView;
import android.widget.Toast;

import com.example.aviya.takeandgo.R;
import com.example.aviya.takeandgo.model.backend.CompanyConst;
import com.example.aviya.takeandgo.model.datasource.DBmanagerFactory;
import com.example.aviya.takeandgo.model.entities.CarModel;
import com.example.aviya.takeandgo.model.entities.GEAR;

public class AddModelActivity extends Activity {

    private CarModel carModel;
    private ImageButton addModelButton;
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
    private TextView seekBarInfo;
    private Spinner spinnerGearBox;
    private CheckBox checkboxIsTurbo;
    private CheckBox checkboxIsConvertible;
    private EditText editTextKilometersPerTank;
    private CheckBox checkboxCodeNeeded;
    private TextView textViewCodeNum;
    private EditText editTextCodeNum;

    private void findViews() {
        carModel = new CarModel();
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
        seekBarInfo = (TextView)findViewById(R.id.seekBarInfo);
        spinnerGearBox = (Spinner)findViewById( R.id.spinnerGearBox );
        checkboxIsTurbo = (CheckBox)findViewById( R.id.checkboxIsTurbo );
        checkboxIsConvertible = (CheckBox)findViewById( R.id.checkboxIsConvertible );
        editTextKilometersPerTank = (EditText)findViewById( R.id.editTextKilometersPerTank );
        checkboxCodeNeeded = (CheckBox)findViewById( R.id.checkboxCodeNeeded );
        textViewCodeNum = (TextView)findViewById(R.id.textViewCodeNum);
        editTextCodeNum = (EditText)findViewById( R.id.editTextCodeNum );
        addModelButton = (ImageButton)findViewById( R.id.addModelButton );
        addModelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    collectDataAdd();
            }
        });
        initData();
    }

    private void collectDataAdd() {
        try{
        carModel.setModelName(editTextCarModelModelName.getText().toString());
        carModel.setSeats(Integer.parseInt(editTextSeats.getText().toString()));
        carModel.setBuildInTurbo(checkboxIsTurbo.isChecked());
        carModel.setKilometersPerTank(Double.parseDouble(editTextKilometersPerTank.getText().toString()));
        carModel.setConvertible(checkboxIsConvertible.isChecked());
        carModel.setCodeRequired(checkboxCodeNeeded.isChecked());
        if (checkboxCodeNeeded.isChecked())
            carModel.setCodeNumber(Integer.parseInt(editTextCodeNum.getText().toString()));
        else
            carModel.setCodeNumber(-1);
        carModel.setDoors(Integer.parseInt(editTextDoors.getText().toString()));
        carModel.setHorsePower(Double.parseDouble(editTextHorsePower.getText().toString()));
        carModel.setAcceleration(Double.parseDouble(editTextAcceleration.getText().toString()));
        carModel.setTrunkVolume(Double.parseDouble(editTextTrunkVolume.getText().toString()));
        carModel.setMaxSpeed(seekbarMaxSpeed.getProgress());
        carModel.setEngineCapacity(Double.parseDouble(editTextEngineCapacity.getText().toString()));
        carModel.setCarCompany(editTextCarCompany.getText().toString());
        carModel.setModelCode(editTextCarModelModelCode.getText().toString());
        final ContentValues cv = CompanyConst.CarModelToContentValues(carModel);
            new AsyncTask<Void, Boolean, Boolean>() {
                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    if(aBoolean){
                        Toast.makeText(getApplicationContext(), R.string.add_model_success,Toast.LENGTH_SHORT).show();
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
                            public void run() {Toast.makeText(AddModelActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();}});
                        return false;
                    }
                }
            }.execute();
        }
        catch(Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void initData() {

        seekbarMaxSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarInfo.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        spinnerGearBox.setAdapter(new ArrayAdapter<GEAR>(this, android.R.layout.simple_list_item_1, GEAR.values()));
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

        textViewCodeNum.setVisibility(View.GONE);
        editTextCodeNum.setVisibility(View.GONE);
        checkboxCodeNeeded.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    textViewCodeNum.setVisibility(View.VISIBLE);
                    editTextCodeNum.setVisibility(View.VISIBLE);
                }
                else
                {

                    textViewCodeNum.setVisibility(View.GONE);
                    editTextCodeNum.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_model);
        findViews();
    }
}
