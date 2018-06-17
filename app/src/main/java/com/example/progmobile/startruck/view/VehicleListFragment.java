package com.example.progmobile.startruck.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.progmobile.startruck.R;
import com.example.progmobile.startruck.model.bean.Driver;
import com.example.progmobile.startruck.model.bean.Vehicle;
import com.example.progmobile.startruck.model.dao.VehicleDAO;

import java.util.ArrayList;

public class VehicleListFragment extends Fragment {
    View vehicleListView;

    ListView vehicleList;
    Driver dd;
    String nameVehicle;
    public static String NAME_VEHICLE = "";
    public static boolean EDIT_VEHICLE = false;

    public static String getNameVehicle() {
        return NAME_VEHICLE;
    }

    public static void setNameVehicle(String nameVehicle) {
        NAME_VEHICLE = nameVehicle;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        vehicleListView = inflater.inflate(R.layout.activity_vehicle_list, container, false);

        vehicleList = vehicleListView.findViewById(R.id.listVehicle);

        NAME_VEHICLE = "";
        EDIT_VEHICLE = false;


        final ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, vehicleArray(getActivity()));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        vehicleList.setAdapter(arrayAdapter);
        registerForContextMenu(vehicleList);


        vehicleList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                nameVehicle = arrayAdapter.getItem(position);
                setNameVehicle(nameVehicle);

                return false;
            }
        });

        return vehicleListView;
    }


    public void onResume(){
        super.onResume();
        vehicleArray(getActivity());
        NAME_VEHICLE = "";
        EDIT_VEHICLE = false;
    }


    public static ArrayList<String> vehicleArray(Context context){
        ArrayList<String> vehicle = new ArrayList<String>();

        VehicleDAO d = new VehicleDAO(context);
        vehicle.clear();

        for(Vehicle dv : d.selectVehicles(MainActivity.usr)) {
            vehicle.add(dv.getNameVehicle());
        }

        return vehicle;
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(Menu.NONE, 0, Menu.NONE, "Excluir veículo");
        menu.add(Menu.NONE, 1, Menu.NONE, "Editar veículo");
        menu.add(Menu.NONE, 2, Menu.NONE, "Alterar status");
    }

    public boolean onContextItemSelected(MenuItem item) {
        FragmentManager fragmentManager = getFragmentManager();

        switch (item.getItemId()) {

            case 0:
                VehicleDAO d = new VehicleDAO(getActivity());
                d.delete(nameVehicle.trim());
                vehicleArray(getActivity());
                fragmentManager.beginTransaction().replace(R.id.fragment, new VehicleListFragment()).commit();

                return true;

            case 1:
                EDIT_VEHICLE = true;
                fragmentManager.beginTransaction().replace(R.id.fragment, new VehicleRegisterFragment()).commit();

                return true;
            case 2:
                fragmentManager.beginTransaction().replace(R.id.fragment, new Status()).commit();

                return true;

        }
        return super.onContextItemSelected(item);
    }





}
