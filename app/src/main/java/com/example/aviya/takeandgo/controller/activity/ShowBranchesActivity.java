package com.example.aviya.takeandgo.controller.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aviya.takeandgo.R;
import com.example.aviya.takeandgo.controller.adapter.MyBranchAdapter;
import com.example.aviya.takeandgo.model.datasource.DBmanagerFactory;
import com.example.aviya.takeandgo.model.entities.Branch;

import java.util.ArrayList;
import java.util.List;

public class ShowBranchesActivity extends Activity {
    ListView branchesListView;
    TextView branchShowWait;
    List<Branch> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_branches);
        branchesListView = (ListView) findViewById(R.id.branchesListView);
        branchShowWait = (TextView)findViewById(R.id.branchShowWait);
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                branchShowWait.setVisibility(View.GONE);
                branchesListView.setAdapter(new MyBranchAdapter(getBaseContext(),list));
            }

            @Override
            protected Void doInBackground(Void... params) {
                list = DBmanagerFactory.getManager().allBranches();
                return null;
            }
        }.execute();
    }
}
