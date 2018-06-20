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
import com.example.progmobile.startruck.model.dao.DriverDAO;

import java.util.ArrayList;

public class DriversListFragment extends Fragment {
    View driversListView;
    ListView drivers;
    public static String driverSelected;
    ArrayAdapter<String> arrayAdapter;

    public static boolean EDIT_DRIVER = false;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        driversListView = inflater.inflate(R.layout.activity_drivers_list, container, false);

        drivers = driversListView.findViewById(R.id.listDrivers);


        arrayAdapter= new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, driverArray(getActivity()));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        drivers.setAdapter(arrayAdapter);
        registerForContextMenu(drivers);


        drivers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                driverSelected = arrayAdapter.getItem(position);
                System.out.println("driver "+ driverSelected);
                return false;
            }
        });


        return driversListView;
    }

    public void onResume(){
        super.onResume();
        driverArray(getActivity());
        EDIT_DRIVER = false;
    }


    public static ArrayList<String> driverArray(Context context){
        ArrayList<String> driver = new ArrayList<String>();
        DriverDAO d = new DriverDAO(context);
        driver.clear();

        for(Driver dv : d.selectDrivers(MainActivity.usr)) {
            driver.add(dv.getName());
        }
        return driver;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(Menu.NONE, 0, Menu.NONE, "Excluir motorista");
        menu.add(Menu.NONE, 1, Menu.NONE, "Editar motorista");
    }

    public boolean onContextItemSelected(MenuItem item) {
        FragmentManager fragmentManager = getFragmentManager();

        switch (item.getItemId()) {
            case 0:
                DriverDAO d = new DriverDAO(getActivity());
                d.delete(driverSelected.trim());
                driverArray(getActivity());
                //arrayAdapter.notifyDataSetChanged();
                return true;

            case 1:
                EDIT_DRIVER = true;
                fragmentManager.beginTransaction().replace(R.id.fragment, new DriverRegisterFragment()).commit();
                return true;

        }
        return super.onContextItemSelected(item);
    }


}
