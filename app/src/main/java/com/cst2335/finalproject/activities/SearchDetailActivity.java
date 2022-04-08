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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cst2335.finalproject.Fragments.ResultFragment;
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
// THIS SEARCH DETAIL ACTIVITY CLASS EXTENDS APP COMPAT ACTIVITY
public class SearchDetailActivity extends AppCompatActivity {
    //DECLARATIONS
    TextView country,fromDate, toDate;
    private ArrayList<CovidData> list  = new ArrayList<>();
    CovidDataAdaptor covidDataAdaptor;
    SQLiteDatabase db;
    ProgressBar progressBar;
    ListView searchdetail_listview;
    Button view_history_button;
    Boolean framelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//CALLED WHEN THE ACTIVITY IS STARTED
        setContentView(R.layout.activity_search_detail);//SET THE LAYOUT VIEW FOR ACTIVITY SEARCH DETAIL

        country=findViewById(R.id.country_textview);//FIND VIEW BY COUNTRY ID
        fromDate=findViewById(R.id.from_date_textview);//FIND VIEW BY FROM DATE ID
        toDate=findViewById(R.id.to_date_textview);//FIND VIEW BY TO DATE TEXTVIEW ID
        progressBar = findViewById(R.id.progressBar);//FIND VIEW BY PROGRESS BAR ID
        searchdetail_listview = findViewById(R.id.search_details_list_view);//FIND VIEW BY SEARCH DETAILS LIST VIEW ID
        view_history_button = findViewById(R.id.view_history_button);//FIND VIEW BY VIEW HISTORY BUTTON ID

        //GET DATA VIA INTENT
        country.setText(getIntent().getStringExtra("country"));
        toDate.setText(getIntent().getStringExtra("toDate"));
        fromDate.setText(getIntent().getStringExtra("fromDate"));

        framelayout = findViewById(R.id.framelayout) != null;//FIND THE VIEW BY ID FOR FRAME LAYOUT

//LONG CLICK FUNCTIONALITY FOR AN ITEM CLICKED
        searchdetail_listview.setOnItemLongClickListener((parent, view, position, id) -> {
            if (framelayout){
                LoadFragment(new ResultFragment(),((CovidData) list.get(position)).getProvince(),String.valueOf(((CovidData) list.get(position)).getGetCases()),
                        ((CovidData) list.get(position)).getDate(),((CovidData) list.get(position)).getDataBaseId());
            }else {
                CovidData selectedData = list.get(position);//SELECTING THE DATA AT A SPECIFIC POSITION
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                Log.e("string", "-->" + this.getString(R.string.additional_details_msg));
                alertDialogBuilder.setTitle(R.string.additional_details_msg)//SET THE TITLE FOR ALERT DIALOG
//DETAIL OF THE CLICKED ITEM
                        .setMessage(this.getString(R.string.the_province_is) + " " + ((CovidData) list.get(position)).getProvince() + "\n" +
                                this.getString(R.string.the_case_number_is) + " " + ((CovidData) list.get(position)).getCaseNumber() + "\n" +
                                this.getString(R.string.the_date_is) + " " + ((CovidData) list.get(position)).getDate().substring(0, 10) + "\n" +
                                this.getString(R.string.the_database_is) + " " + ((CovidData) list.get(position)).getDataBaseId())
                        .setNeutralButton(R.string.okay, (click, arg) -> {
                        })
                        .create().show();
            }
            return true;
        });
//FUNCTIONALITY FOR WHEN WE CLICK VIEW HISTORY

        view_history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Viewhister","");
                Intent intent = new Intent(SearchDetailActivity.this,StoredActivity.class);
                startActivity(intent);
                finish();
            }
        });

//CREATE NEW QUERY FOR COVIDDATAQUERY OBJECT
        CovidDataQuery cdq = new CovidDataQuery();
        cdq.execute("https://api.covid19api.com/country/"+country.getText().toString().trim().toUpperCase()+"/status/confirmed/live?from="+fromDate.getText().toString().trim()+"T00:00:00Z&to="+toDate.getText().toString().trim()+"T00:00:00Z");
        searchdetail_listview.setAdapter(covidDataAdaptor= new CovidDataAdaptor());
        covidDataAdaptor.notifyDataSetChanged();
    }
    public void setUpDatabaseOpener(){
        DatabaseHandler dbOpener = new DatabaseHandler(this);
        db = dbOpener.getWritableDatabase();

    }
//THIS CLASS EXTENDS ASYNCTASK
    private class CovidDataQuery extends AsyncTask< String, Integer, String> {
        // DO THE TASK IN BACK GROUND
        public String doInBackground(String... args)
        {
            try {
                //ENCODE THE STRING URL;
                //CREATE URL OBJECT OF WHAT SERVER TO CONTACT
                URL covid_api_url = new URL(args[0]);
                //OPEN THE CONNECTION
                HttpURLConnection connection = (HttpURLConnection) covid_api_url.openConnection();
                //WAITING FOR THE DATA
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

                        //GET THE DATA ONE BY ONE ADDING INTO LIST
                        list.add(covidData);
                        //STORE THE DATA IN TO SQL DATA BASE
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

                        //INSERT INTO DATABASE
                        long newId = db.insert(DatabaseHandler.TABLE_NAME, null, newRowValue);
                    }
                }
                Thread.sleep(500); // SLEEP FOR 500MILLI SECONDS
                publishProgress(100);  // SET THE PROGRESS BAR VALUE.
            }
            catch (Exception e)
            {
            }
            return "Done";
        }

        // ON PROGRESS UPDATE METHOD SETS THE PROGRESS BAR VISIBILITY TRUE AND  SET THE VALUE.
        public void onProgressUpdate(Integer... args)
        {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(args[0]);
        }

        // THIS METHOD WILL BE EXECUTED AT THE END OF THE TASK
        // THIS WILL SET THE PROGRESS BAR TO IN VISIBLE
        // AND NOTIFY THE CHANGED DATA
        public void onPostExecute(String fromDoInBackground)
        {
            Log.i("CovidDataQuery ", fromDoInBackground);
            progressBar.setVisibility(View.INVISIBLE);
            covidDataAdaptor.notifyDataSetChanged();


        }
    }
//THIS COVIDDATAADAPTOR CLASS EXTENDS THE BASEADAPTER
    private class CovidDataAdaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        //RETURN THE OBJECT FROM THE LIST BASED ON THE POSITION
        @Override
        public CovidData getItem(int position) {
            return list.get(position);
        }

        // GET VIEW FOR COVID DATA
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

        // GET THE ITEM ID
        @Override
        public long getItemId(int position) {
            return Long.parseLong(getItem(position).getDataBaseId());
        }


    }
//LOAD THE FRAGMENT
    protected void LoadFragment(Fragment fragment, String provice, String cases, String date,String databaseid) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("province",provice);
        bundle.putString("cases",cases);
        bundle.putString("date",date);
        bundle.putString("databaseid",databaseid);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.commit();
    }
}