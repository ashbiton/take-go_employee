package com.example.aviya.takeandgo.controller.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aviya.takeandgo.R;
import com.example.aviya.takeandgo.controller.adapter.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends AppCompatActivity {
    private Spinner colorSpinner;
    private List<Integer> colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        colorSpinner=(Spinner)findViewById(R.id.colorSpinner);
        colors = new ArrayList<Integer>();
        colors.add(Color.RED);
        colors.add(Color.CYAN);
        colors.add(Color.GRAY);
        colors.add(Color.BLACK);
        colors.add(Color.MAGENTA);
        colors.add(Color.BLUE);
//
//        Color.CYAN,Color.GRAY,Color.BLACK,Color.MAGENTA,
//                Color.BLUE,Color.WHITE,Color.GREEN,Color.YELLOW
        SpinnerAdapter adapter = new SpinnerAdapter(colors,DemoActivity.this);
        colorSpinner.setAdapter(adapter);
        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),"the chosen color is "+colors.get(position)+" ",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
