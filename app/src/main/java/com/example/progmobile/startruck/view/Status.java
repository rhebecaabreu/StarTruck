package com.example.progmobile.startruck.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progmobile.startruck.R;
import com.example.progmobile.startruck.controller.VehicleController;
import com.example.progmobile.startruck.model.bean.Vehicle;
import com.example.progmobile.startruck.model.dao.VehicleDAO;

public class Status extends Fragment{

    View status;
    String statusVehicle;
    TextView vehicleName, type;

    String name;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        status = inflater.inflate(R.layout.activity_status_vehicle, container, false);

        vehicleName = status.findViewById(R.id.txtName);
        type = status.findViewById(R.id.txtType);
        VehicleDAO x = new VehicleDAO(getActivity());

        name = VehicleListFragment.NAME_VEHICLE;
        vehicleName.setText("Nome do veículo: "+name);
        type.setText("Tipo de veículo: "+ x.selectTypeByName(name));

        final Spinner spinner = (Spinner) status.findViewById(R.id.spinnerStatus);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinnerStatus, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                statusVehicle = spinner.getSelectedItem().toString();

                if(statusVehicle.equals("Selecione")){


                } else {


                }
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });


        Button update = status.findViewById(R.id.btnUpdateStatus);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VehicleDAO x = new VehicleDAO(getActivity());
                x.update(name,statusVehicle);            }
        });



        return status;
    }


}
