package com.example.aviya.takeandgo.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aviya.takeandgo.R;

public class MenuActivity extends Activity implements View.OnClickListener {
    private Button addCarButton;
    private Button addCarModelButton;
    private Button addClientButton;
    private Button showAllCarsButton;
    private Button showAllCarModelsButton;
    private Button showAllBranchesButton;
    private Button showAllUsersButton;


    private void findViews() {
        addCarButton = (Button)findViewById( R.id.addCarButton );
        addCarModelButton = (Button)findViewById( R.id.addCarModelButton );
        addClientButton = (Button)findViewById(R.id.addClientButton);
        showAllCarsButton = (Button)findViewById( R.id.showAllCarsButton );
        showAllCarModelsButton = (Button)findViewById( R.id.showAllCarModelsButton );
        showAllBranchesButton = (Button)findViewById( R.id.showAllBranchesButton );
        showAllUsersButton = (Button)findViewById(R.id.showAllUsersButton);

        addCarButton.setOnClickListener( this );
        addCarModelButton.setOnClickListener( this );
        addClientButton.setOnClickListener(this);
        showAllCarsButton.setOnClickListener( this );
        showAllCarModelsButton.setOnClickListener( this );
        showAllBranchesButton.setOnClickListener( this );
        showAllUsersButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if ( v == addCarButton ) {
            intent = new Intent(this, AddCarActivity.class);
        } else if ( v == addCarModelButton ) {
            intent = new Intent(this,AddModelActivity.class);
        } else if ( v == addClientButton ) {
            intent = new Intent(this,AddClientActivity.class);
        } else if ( v == showAllCarsButton ) {
            intent = new Intent(this,ShowCarsActivity.class);
        } else if ( v == showAllCarModelsButton ) {
            intent = new Intent(this, ShowModelsActivity.class);
        } else if ( v == showAllBranchesButton ) {
            intent = new Intent(this, ShowBranchesActivity.class);
        } else if ( v == showAllUsersButton ) {
            intent = new Intent(this, ShowUsersActivity.class);
        }
        try{startActivity(intent);}
        catch (Exception e){e.printStackTrace();}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViews();
    }
}
