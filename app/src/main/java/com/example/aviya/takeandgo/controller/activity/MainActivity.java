package com.example.aviya.takeandgo.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aviya.takeandgo.*;

public class MainActivity extends Activity implements View.OnClickListener {


    private Button signUpButton;
    private Button loginButton;
    private Button demoButton;

    private void findViews() {
        signUpButton = (Button)findViewById( R.id.signUpButton );
        loginButton = (Button)findViewById( R.id.loginButton );

        demoButton = (Button)findViewById(R.id.demoButton1);
        demoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(in);
            }
        });

        signUpButton.setOnClickListener( this );
        loginButton.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if ( v == signUpButton ) {
            intent = new Intent(this,AddUserActivity.class);
        } else if ( v == loginButton ) {
            intent = new Intent(this,LoginActivity.class);
        }
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }
}
