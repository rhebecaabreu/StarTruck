package com.example.progmobile.startruck.view;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.progmobile.startruck.R;

public class DriversListFragment extends Fragment {
    View driversListView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        driversListView = inflater.inflate(R.layout.activity_drivers_list, container, false);
      return driversListView;
    }




}
