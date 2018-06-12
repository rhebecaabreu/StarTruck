package com.example.progmobile.startruck.view;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.progmobile.startruck.R;
import com.example.progmobile.startruck.controller.VehicleController;
import com.example.progmobile.startruck.model.bean.Vehicle;
import com.example.progmobile.startruck.model.dao.VehicleDAO;

public class VehicleRegisterFragment extends Fragment {
    View vehicleRegisterFragment;
    String typeFuel="";
    String typeVehicle="";
    boolean heavyLoad = false;


    EditText nameVehicle, plate, mark, axes, capacity, observation;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vehicleRegisterFragment = inflater.inflate(R.layout.fragment_vehicle_register, container, false);

        final Spinner spinner = (Spinner) vehicleRegisterFragment.findViewById(R.id.spinnerTypeV);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinnerTypeVehicle, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                typeVehicle = spinner.getSelectedItem().toString();

                if(typeVehicle.equals("Caminhão") || typeVehicle.equals("Carreta")){
                    capacity.setEnabled(true);
                    axes.setEnabled(true);

                    heavyLoad = true;

                } else {
                    capacity.setEnabled(false);
                    axes.setEnabled(false);


                }
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });



        final Spinner spinner2 = (Spinner) vehicleRegisterFragment.findViewById(R.id.spinnerFuel);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinnerTypeFuel, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                typeFuel = spinner2.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        nameVehicle = vehicleRegisterFragment.findViewById(R.id.edtVehicleName);
        plate = vehicleRegisterFragment.findViewById(R.id.edtPlate);
        mark = vehicleRegisterFragment.findViewById(R.id.edtMark);
        axes = vehicleRegisterFragment.findViewById(R.id.edtAxes);
        capacity = vehicleRegisterFragment.findViewById(R.id.edtCapacity);
        observation = vehicleRegisterFragment.findViewById(R.id.edtObservation);

        Button saveVehicle = vehicleRegisterFragment.findViewById(R.id.btnSaveVehicle);
        saveVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameVehicle.getText().toString();
                String markk = mark.getText().toString();
                String platee = plate.getText().toString();
                String observationn = observation.getText().toString();

                String axess = axes.getText().toString();
                String capacityy = capacity.getText().toString();

                if(name.trim().isEmpty() || markk.trim().isEmpty() || platee.trim().isEmpty()
                        ||  typeFuel.equals("Selecione") || typeVehicle.equals("Selecione")
                        ){

                    Toast.makeText(getActivity(),"Por favor, preencha todos os campos!",Toast.LENGTH_SHORT).show();

                }else if (heavyLoad == true && (axess.trim().isEmpty() || capacityy.trim().isEmpty())){
                         Toast.makeText(getActivity(),"Por favor, preencha todos os campos!",Toast.LENGTH_SHORT).show();

                } else {
                    VehicleController vdao = new VehicleController();

                    if(heavyLoad==true){
                        vdao.save(getActivity(), typeVehicle, name, platee, markk, typeFuel, Integer.parseInt(axess), Integer.parseInt(capacityy), observationn);
                        Toast.makeText(getActivity(),"Cadastro realizado com sucesso",Toast.LENGTH_SHORT).show();
                    } else {
                        vdao.save2(getActivity(),typeVehicle, name, platee, markk, typeFuel, observationn);
                        Toast.makeText(getActivity(),"Cadastro realizado com sucesso",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return vehicleRegisterFragment ;
    }



}
