package com.example.progmobile.startruck.view;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.progmobile.startruck.R;

public class VehicleRegisterFragment extends Fragment {
    View vehicleRegisterFragment;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vehicleRegisterFragment = inflater.inflate(R.layout.fragment_vehicle_register, container, false);

        Spinner spinner = (Spinner) vehicleRegisterFragment.findViewById(R.id.spinnerTypeV);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinnerTypeVehicle, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinner2 = (Spinner) vehicleRegisterFragment.findViewById(R.id.spinnerFuel);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinnerTypeFuel, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);


        return vehicleRegisterFragment ;
    }




}
