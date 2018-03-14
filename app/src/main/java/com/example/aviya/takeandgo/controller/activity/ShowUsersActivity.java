package com.example.aviya.takeandgo.controller.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aviya.takeandgo.R;
import com.example.aviya.takeandgo.controller.adapter.MyUserAdapter;
import com.example.aviya.takeandgo.model.datasource.DBmanagerFactory;
import com.example.aviya.takeandgo.model.entities.User;

import java.util.List;

public class ShowUsersActivity extends Activity {

    ListView usersListView;
    TextView userShowWait;
    List<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);
        usersListView = (ListView) findViewById(R.id.usersListView);
        userShowWait = (TextView) findViewById(R.id.userShowWait);
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                userShowWait.setVisibility(View.GONE);
                usersListView.setAdapter(new MyUserAdapter(getBaseContext(),list));
            }

            @Override
            protected Void doInBackground(Void... params) {
                list = DBmanagerFactory.getManager().allUsers();
                return null;
            }
        }.execute();
    }
}
