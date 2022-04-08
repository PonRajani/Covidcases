package com.cst2335.finalproject.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.cst2335.finalproject.R;


import java.util.List;

//THIS IS THE CLASS FOR RESULT FRAGMENT EXTENDS FRAGMENT
//WHEN WE CLICK ON THE ONE OF THE DATA RETRIEVED FROM THE API THIS METHOD WILL GIVE YOU THE DETAIL OF THAT DATA ON THE FRAGMENT

public class ResultFragment extends Fragment {
    View view;
    TextView province_textview,date_textview,database_textview,cases_textview;
    Button okay_button;
    AppCompatActivity parentActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        parentActivity = (AppCompatActivity) context;
    }

    @Nullable
    @Override
    //CREATE THE VIEW FOR FRAGMENT
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_result, container, false);
    //FIND VIEW BY ID
        province_textview = view.findViewById(R.id.province_textview);
        date_textview = view.findViewById(R.id.date_textview);
        database_textview = view.findViewById(R.id.database_id_textview);
        cases_textview = view.findViewById(R.id.confirmed_cases_textview);
        okay_button = view.findViewById(R.id.okay_button);
    //GET PROVINCE,CASES,DATABASE ID,DATE
        String province = this.getArguments().getString("province");
        String cases = this.getArguments().getString("cases");
        String databaseid = this.getArguments().getString("databaseid");
        String date = this.getArguments().getString("date");

    //SET PROVINCE,CASES,DATABASE ID,DATE
        province_textview.setText(province);
        cases_textview.setText(cases);
        database_textview.setText(databaseid);
        date_textview.setText(date);
        okay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentActivity.getSupportFragmentManager().beginTransaction().remove(ResultFragment.this).commit();
            }
        });

        return view;
    }

}
