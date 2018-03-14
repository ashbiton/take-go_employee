package com.example.aviya.takeandgo.controller.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aviya.takeandgo.R;
import com.example.aviya.takeandgo.model.backend.CompanyChecks;
import com.example.aviya.takeandgo.model.backend.CompanyConst;
import com.example.aviya.takeandgo.model.datasource.DBmanagerFactory;
import com.example.aviya.takeandgo.model.entities.User;

public class AddUserActivity extends Activity {

    private EditText editText;
    private EditText editText6;
    private ImageButton imageButton;
    private TextView addUserWait;
    private User user;


    private void findViews() {
        editText = (EditText)findViewById( R.id.editText );
        editText6 = (EditText)findViewById( R.id.editText6 );
        imageButton = (ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    addUser();
            }
        });
        addUserWait = (TextView) findViewById(R.id.addUserWait);
        user = new User();

    }

        private void addUser() {
            try
            {
                imageButton.setClickable(false);
                user.setUserName(editText.getText().toString().toLowerCase().trim());
                user.setPassword(editText6.getText().toString());
                CompanyChecks.User.checkPassword(user.getPassword());
                addUserWait.setVisibility(View.VISIBLE);
                final ContentValues cv = CompanyConst.UserToContentValues(user);
                new AsyncTask<Void, Boolean, Boolean>() {
                    @Override
                    protected void onPostExecute(Boolean aBoolean) {
                        addUserWait.setVisibility(View.GONE);
                        if(aBoolean)
                        {
                            Toast.makeText(getApplicationContext(),R.string.add_user_success,Toast.LENGTH_LONG).show();
                            Intent in = new Intent(getBaseContext(),MenuActivity.class);
                            startActivity(in);
                        }
                        imageButton.setClickable(true);
                    }

                    @Override
                    protected Boolean doInBackground(Void... params) {
                        try{
                            DBmanagerFactory.getManager().addUser(cv);
                            return true;
                        }
                        catch (final Exception e)
                        {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(AddUserActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                                }
                            });
                            return false;
                        }
                    }
                }.execute();

        }
        catch(Exception c)
        {
            Toast.makeText(this,c.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        setTitle("ADD NEW USER");
        findViews();
    }
}
