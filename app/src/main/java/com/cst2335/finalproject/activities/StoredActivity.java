package com.cst2335.finalproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.cst2335.finalproject.R;

import com.cst2335.finalproject.adapters.DataAdapter;
import com.cst2335.finalproject.databases.DatabaseHandler;
import com.cst2335.finalproject.models.CovidData;
import java.util.List;
//THIS CLASS EXTENDS APPCOMPATACTIVITY
public class StoredActivity extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    ListView listView;
    DataAdapter dataAdapter;
//FUNCTIONALITY WHEN THE BACK BUTTON PRESSED
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(StoredActivity.this, MainActivity.class);//THIS WILL TAKE YOU TO STORED ACTIVITY TO MAIN ACTIVITY
        startActivity(intent);//START THE ACTIVITY
        finish();//FINISH THE ACTIVITY
    }
//METHOD TO STORE THE DATA INTO LIST VIEW
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stored);//SET THE CONTENT VIEW

        databaseHandler = new DatabaseHandler(StoredActivity.this);//CREATE  A NEW OBJECT

        listView = findViewById(R.id.list_view);//FIND THE VIEW BY ID
        List<CovidData> dataList = databaseHandler.getAllData();//STORE ALL THE DATA

        Log.e("listlist","-->"+dataList.size());

        dataAdapter = new DataAdapter(StoredActivity.this, dataList, true);
        listView.setAdapter(dataAdapter);
    }
}