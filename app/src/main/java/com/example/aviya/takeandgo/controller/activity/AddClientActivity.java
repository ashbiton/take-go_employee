package com.example.aviya.takeandgo.controller.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aviya.takeandgo.R;
import com.example.aviya.takeandgo.model.backend.CompanyConst;
import com.example.aviya.takeandgo.model.datasource.DBmanagerFactory;
import com.example.aviya.takeandgo.model.entities.Client;

public class AddClientActivity extends Activity implements View.OnClickListener {

    Client client;
    private ImageButton imageButtonAddClient;
    private EditText editTextClientID;
    private EditText editTextClientName;
    private EditText editTextClientLastName;
    private EditText editTextClientPhone;
    private EditText editTextClientEmail;
    private EditText editTextClientCreditNumber;


    private void findViews() {
        client = new Client();
        imageButtonAddClient = (ImageButton)findViewById( R.id.imageButtonAddClient );
        editTextClientID = (EditText)findViewById( R.id.editTextClientID );
        editTextClientName = (EditText)findViewById( R.id.editTextClientName );
        editTextClientLastName = (EditText)findViewById( R.id.editTextClientLastName );
        editTextClientPhone = (EditText)findViewById( R.id.editTextClientPhone );
        editTextClientEmail = (EditText)findViewById( R.id.editTextClientEmail );
        editTextClientCreditNumber = (EditText)findViewById( R.id.editTextClientCreditNumber );

        imageButtonAddClient.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if ( v == imageButtonAddClient ) {
            collectData();
            addClient();
        }
    }

    private void collectData() {
        client.setName(editTextClientName.getText().toString());
        client.setPhone(editTextClientPhone.getText().toString());
        client.setCreditCardNumber(editTextClientCreditNumber.getText().toString());
        client.setEmailAddress(editTextClientEmail.getText().toString());
        client.setLastName(editTextClientLastName.getText().toString());
        client.set_id(Integer.parseInt(editTextClientID.getText().toString()));
    }

    private void addClient() {
        try {
            final ContentValues cv = CompanyConst.ClientToContentValues(client);
            new AsyncTask<Void, Boolean, Boolean>() {
                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    if (aBoolean) {
                        Toast.makeText(getApplicationContext(), R.string.add_client_success, Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                protected Boolean doInBackground(Void... params) {
                    try {
                        DBmanagerFactory.getManager().addClient(cv);
                        return true;
                    } catch (final Exception e) {
                        runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(AddClientActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                        return false;
                    }
                }
            }.execute();
        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        findViews();
    }
}
