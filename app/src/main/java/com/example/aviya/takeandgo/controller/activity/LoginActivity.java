package com.example.aviya.takeandgo.controller.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aviya.takeandgo.R;
import com.example.aviya.takeandgo.model.backend.CompanyConst;
import com.example.aviya.takeandgo.model.datasource.DBmanagerFactory;
import com.example.aviya.takeandgo.model.entities.User;

public class LoginActivity extends Activity {
    User u;
    TextView loginWaitText;
    Button endLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        endLoginButton = (Button) findViewById(R.id.endLoginButton);
        loginWaitText = (TextView) findViewById(R.id.loginWaitText);
        endLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        try
        {
            endLoginButton.setClickable(false);
            String name = ((EditText) findViewById(R.id.nameEditText)).getText().toString().toLowerCase().trim();
            String password = ((EditText) findViewById(R.id.passwordEditText)).getText().toString().trim();
            u = new User(name, password);
            loginWaitText.setVisibility(View.VISIBLE);
            final ContentValues contentValues = CompanyConst.UserToContentValues(u);
            new AsyncTask<Void, Boolean, Boolean>() {
                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    loginWaitText.setVisibility(View.GONE);
                    if (aBoolean)
                    {
                        Intent in = new Intent(getBaseContext(), MenuActivity.class);
                        startActivity(in);
                    }
                    else
                        Toast.makeText(getApplicationContext(), R.string.user_not_exist, Toast.LENGTH_LONG).show();
                    endLoginButton.setClickable(true);
                }

                @Override
                protected Boolean doInBackground(Void... params) {
                    return DBmanagerFactory.getManager().existUser(contentValues);
                }
            }.execute();

        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
