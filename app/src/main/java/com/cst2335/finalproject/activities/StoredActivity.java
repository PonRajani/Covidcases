package com.cst2335.finalproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.cst2335.finalproject.R;

import com.cst2335.finalproject.adapters.DataAdapter;
import com.cst2335.finalproject.databases.DatabaseHandler;
import com.cst2335.finalproject.models.CovidData;
import java.util.List;

public class StoredActivity extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    ListView listView;
    DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stored);

        databaseHandler = new DatabaseHandler(StoredActivity.this);

        listView = findViewById(R.id.list_view);
        List<CovidData> dataList = databaseHandler.getAllData();

        Log.e("listlist","-->"+dataList.size());

        dataAdapter = new DataAdapter(StoredActivity.this, dataList, true);
        listView.setAdapter(dataAdapter);
    }
}