package com.cst2335.finalproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.cst2335.finalproject.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    Button search_button;
    EditText country_edit_text, from_date_edit_text, to_date_edit_text;
    SharedPreferences sharedPreferences;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.home_icon:
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.search_icon:
                Intent intent1=new Intent(this,MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.help_icon:
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
                alertDialog.setTitle("Information For User")
                        .setMessage("Please enter the country name and the date in the form of YYYY_MM_DD then hit search")
                        .setNeutralButton("Okay",(click, arg)->{})
                        .show();
                break;

        }
        return true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        search_button=findViewById(R.id.search_button);
        country_edit_text=findViewById(R.id.country_edit_text);
        from_date_edit_text=findViewById(R.id.from_date_edit_text);
        to_date_edit_text=findViewById(R.id.to_date_edit_text);
        sharedPreferences = getSharedPreferences("final_project_pref",MODE_PRIVATE);

        if (!sharedPreferences.getString("country","").equals("")){
            country_edit_text.setText(sharedPreferences.getString("country",""));
            from_date_edit_text.setText(sharedPreferences.getString("from_date",""));
            to_date_edit_text.setText(sharedPreferences.getString("to_date",""));
        }

        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle= new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        NavigationView navigationView=findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_search_item:
                        Intent intent1 = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intent1);
                        finish();
                        Log.e("Search ", ""+item.getItemId());
                    case R.id.navigation_view_history_item:
                        Log.e("View History ", ""+item.getItemId());

                }
                //close navigation
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            }
        });
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!country_edit_text.getText().toString().equals("")){
                    if (!from_date_edit_text.getText().toString().equals("")){
                        if (!to_date_edit_text.getText().toString().equals("")){

                            SharedPreferences.Editor myEdit = sharedPreferences.edit();

                            myEdit.putString("country", country_edit_text.getText().toString());
                            myEdit.putString("from_date", from_date_edit_text.getText().toString());
                            myEdit.putString("to_date", to_date_edit_text.getText().toString());

                            myEdit.commit();

                            Intent intent = new Intent(MainActivity.this,SearchDetailActivity.class);
                            intent.putExtra("country",country_edit_text.getText().toString());
                            intent.putExtra("fromDate",from_date_edit_text.getText().toString());
                            intent.putExtra("toDate", to_date_edit_text.getText().toString());
                            startActivity(intent);

                        }else {
                            Toast.makeText(MainActivity.this, "Please enter to date", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(MainActivity.this, "Please enter from date", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Please enter country", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}