package com.example.progmobile.startruck.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import com.example.progmobile.startruck.model.dao.DriverDAO;
import com.example.progmobile.startruck.model.dao.VehicleDAO;

import java.util.ArrayList;

public class VehicleListFragment extends Fragment {
    View vehicleListView;

    ListView vehicleList;
    Driver dd;
    String yy;
    public static String NAME_VEHICLE;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        vehicleListView = inflater.inflate(R.layout.activity_vehicle_list, container, false);

        vehicleList = vehicleListView.findViewById(R.id.listVehicle);

        final ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, vehicleArray(getActivity()));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        vehicleList.setAdapter(arrayAdapter);
        registerForContextMenu(vehicleList);


        vehicleList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                yy = arrayAdapter.getItem(position);

                return false;
            }
        });

        NAME_VEHICLE=yy;


        return vehicleListView;
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
        switch (item.getItemId()) {
            case 0:
                VehicleDAO d = new VehicleDAO(getActivity());
                d.delete(yy.trim());
                vehicleArray(getActivity());
                return true;

            case 1:
                Log.i("ContextMenu", "Item 2a was chosen");
                return true;
            case 2:

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment, new Status()).commit();

                return true;

        }
        return super.onContextItemSelected(item);
    }





}
