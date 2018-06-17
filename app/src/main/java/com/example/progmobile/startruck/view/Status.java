package com.example.progmobile.startruck.view;

import android.app.Fragment;
import android.app.FragmentManager;
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

    String name, name2;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        status = inflater.inflate(R.layout.activity_status_vehicle, container, false);

        vehicleName = status.findViewById(R.id.txtName);
        type = status.findViewById(R.id.txtType);
        VehicleDAO x = new VehicleDAO(getActivity());

        name = VehicleListFragment.getNameVehicle();


        System.out.println(name);
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

                System.out.println(statusVehicle);
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
                if(statusVehicle.equals("Selecione")){
                    Toast.makeText(getActivity(),"Por favor, selecione o status que deseja atribuir ao veículo!",Toast.LENGTH_SHORT).show();

                }else {
                    x.update(name, statusVehicle);
                    Toast.makeText(getActivity(),"Status atualizado com sucesso!",Toast.LENGTH_SHORT).show();
                    FragmentManager fragmentManager = getFragmentManager();

                    fragmentManager.beginTransaction().replace(R.id.fragment, new HomeFragment()).commit();

                }

            }
        });



        return status;
    }


}
