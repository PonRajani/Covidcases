package com.cst2335.finalproject.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.cst2335.finalproject.R;
import com.cst2335.finalproject.databases.DatabaseHandler;
import com.cst2335.finalproject.models.CovidData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SearchDetailActivity extends AppCompatActivity {
    TextView country,fromDate, toDate;
    private ArrayList<CovidData> list  = new ArrayList<>();
    CovidDataAdaptor covidDataAdaptor;
    SQLiteDatabase db;
    ProgressBar progressBar;
    ListView searchdetail_listview;
    Button view_history_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);

        country=findViewById(R.id.country_textview);
        fromDate=findViewById(R.id.from_date_textview);
        toDate=findViewById(R.id.to_date_textview);
        progressBar = findViewById(R.id.progressBar);
        searchdetail_listview = findViewById(R.id.search_details_list_view);
        view_history_button = findViewById(R.id.view_history_button);

        // Get the 3 data through intent then set into default text view on this activity
        country.setText(getIntent().getStringExtra("country"));
        toDate.setText(getIntent().getStringExtra("toDate"));
        fromDate.setText(getIntent().getStringExtra("fromDate"));

//Long click for list view
        searchdetail_listview.setOnItemLongClickListener((parent, view, position, id) -> {
            CovidData selectedData = list.get(position);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Additional Details of Selected Row:")

                    .setMessage("The province is :"+((CovidData)list.get(position)).getProvince()+"\n"+
                            "The Confirmed Case Number :"+((CovidData)list.get(position)).getCaseNumber()+"\n"+
                            "The date is :"+((CovidData)list.get(position)).getDate().substring(0,10)+"\n"+
                            "The database id is"+((CovidData)list.get(position)).getDataBaseId())
                    .setNeutralButton("Okay",(click, arg)->{})
                    .create().show();

            return true;
        });
//view history button on click got to stored activity
        view_history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Viewhister","");
                Intent intent = new Intent(SearchDetailActivity.this,StoredActivity.class);
                startActivity(intent);
                finish();
            }
        });


        CovidDataQuery cdq = new CovidDataQuery();
        cdq.execute("https://api.covid19api.com/country/"+country.getText().toString().trim().toUpperCase()+"/status/confirmed/live?from="+fromDate.getText().toString().trim()+"T00:00:00Z&to="+toDate.getText().toString().trim()+"T00:00:00Z");
        searchdetail_listview.setAdapter(covidDataAdaptor= new CovidDataAdaptor());
        covidDataAdaptor.notifyDataSetChanged();
    }
    public void setUpDatabaseOpener(){
        DatabaseHandler dbOpener = new DatabaseHandler(this);
        db = dbOpener.getWritableDatabase();

    }

    private class CovidDataQuery extends AsyncTask< String, Integer, String> {
        // do the task in background
        public String doInBackground(String... args)
        {
            try {
                //encode the string url; may need to be commented later
                // String encodeURL = URLEncoder.encode(args[0], "UTF-8");
                //create a URL object of what server to contact:
                URL covid_api_url = new URL(args[0]);
                //open the connection
                HttpURLConnection connection = (HttpURLConnection) covid_api_url.openConnection();
                //wait for data:
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                Thread.sleep(500); // sleep for 500 milli seconds
                publishProgress(25); // set the progressbar value
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null)
                {
                    stringBuilder.append(line + "\n");
                }
                String result = stringBuilder.toString();
                //getting the array of data from the link
                JSONArray covidDataArray = new JSONArray(result);

                Log.e("covid_api_result",""+result);

                setUpDatabaseOpener();
                Thread.sleep(500); // sleep for 500 milli seconds
                publishProgress(50);  // set the progressbar value
                for(int databaseid=0; databaseid<covidDataArray.length(); databaseid++){
                    JSONObject covidObject = covidDataArray.getJSONObject(databaseid);
                    String province = covidObject.getString("Province");
                    int caseNumber =covidObject.getInt("Cases");

                    String date = covidObject.getString("Date");
                    String Country = covidObject.getString("Country");
                    String CityCode = covidObject.getString("CityCode");
                    String Status = covidObject.getString("Status");
                    String Lon = covidObject.getString("Lon");
                    String City = covidObject.getString("City");
                    String CountryCode = covidObject.getString("CountryCode");
                    String Lat = covidObject.getString("Lat");


                    if(!province.trim().isEmpty()) {
                        CovidData covidData = new CovidData(province, caseNumber,String.valueOf(databaseid),date,
                                Country,CityCode,Status,Lon,City,CountryCode,Lat);

                        //get the data one by one and add into list
                        list.add(covidData);
                        //Store the data into sql data base
                        ContentValues newRowValue = new ContentValues();
                        newRowValue.put(DatabaseHandler.COLUMN_COUNTRY, Country);
                        newRowValue.put(DatabaseHandler.COLUMN_PROVINCE, province);
                        newRowValue.put(DatabaseHandler.COLUMN_CASES, caseNumber);
                        newRowValue.put(DatabaseHandler.DATABASEID, databaseid);
                        newRowValue.put(DatabaseHandler.COLUMN_CITY_CODE, CityCode);
                        newRowValue.put(DatabaseHandler.COLUMN_STATUS, Status);
                        newRowValue.put(DatabaseHandler.COLUMN_LON, Lon);
                        newRowValue.put(DatabaseHandler.COLUMN_CITY,City);
                        newRowValue.put(DatabaseHandler.COLUMN_COUNTRY_CODE, CountryCode);
                        newRowValue.put(DatabaseHandler.COLUMN_DATE, date);
                        newRowValue.put(DatabaseHandler.COLUMN_LAT, Lat);

                        //Now insert in the database:
                        long newId = db.insert(DatabaseHandler.TABLE_NAME, null, newRowValue);
                    }
                }
                Thread.sleep(500); // sleep for 500 milli seconds
                publishProgress(100);  // set the progressbar value
            }
            catch (Exception e)
            {
            }
            return "Done";
        }

        // onProgressUpdate method, it sets the progress bar visibility to true and set the value
        public void onProgressUpdate(Integer... args)
        {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(args[0]);
        }

        // this method will be executed at the end of the task
        // this will set the progress bar to invisible
        // and notify the data change
        public void onPostExecute(String fromDoInBackground)
        {
            Log.i("CovidDataQuery ", fromDoInBackground);
            progressBar.setVisibility(View.INVISIBLE);
            covidDataAdaptor.notifyDataSetChanged();


        }
    }

    private class CovidDataAdaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        // return the object from the list based on the position
        @Override
        public CovidData getItem(int position) {
            return list.get(position);
        }

        // create the view for CovidData
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater =getLayoutInflater();
            View newView = convertView;
            //Takes to data layout
            newView =inflater.inflate(R.layout.data_layout, parent, false);
            TextView province = newView.findViewById(R.id.province);
            TextView caseNumber = newView.findViewById(R.id.case_number);
            province.setText(getItem(position).getProvince());
            caseNumber.setText(String.valueOf(getItem(position).getCaseNumber()));

            return newView;
        }

        // get the database id of the system
        @Override
        public long getItemId(int position) {
            return Long.parseLong(getItem(position).getDataBaseId());
        }


    }
}