package com.example.tashtash.myrecycleview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.Parse;

public class MainActivity extends AppCompatActivity  {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ITaskController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Parse.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        controller = new TaskController();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(controller.GetTasks());
        mRecyclerView.setAdapter(mAdapter);
        System.out.println("jkdjfkasjdfklasdjf");

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE_ADD_TASK && resultCode == Activity.RESULT_OK) {
            String description = data.getStringExtra(Constants.ADD_NEW_TASK);
            if (description != null) {
                controller.addTask(description);
                mAdapter.notifyDataSetChanged();

            }
        }
    }

    public void addTask(View view) {
       // Log.i(TAG, "addTask was called");
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivityForResult(intent, Constants.REQUEST_CODE_ADD_TASK);
    }


}
