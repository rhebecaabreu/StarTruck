package com.example.progmobile.startruck.view;

import android.app.Fragment;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.EditText;
import android.widget.ListView;

import com.example.progmobile.startruck.R;
import com.example.progmobile.startruck.model.bean.Driver;
import com.example.progmobile.startruck.model.dao.DriverDAO;

import java.util.ArrayList;
import java.util.List;

public class DriversListFragment extends Fragment {
    View driversListView;

    ListView drivers;
    Driver dd;

    String yy;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        driversListView = inflater.inflate(R.layout.activity_drivers_list, container, false);

        drivers = driversListView.findViewById(R.id.listDrivers);


        final ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, driverArray(getActivity()));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        drivers.setAdapter(arrayAdapter);
        registerForContextMenu(drivers);


        drivers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                yy = arrayAdapter.getItem(position);

               System.out.println("driver "+yy);
                return false;
            }
        });


        return driversListView;
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
    }

    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                DriverDAO d = new DriverDAO(getActivity());
                d.delete(yy.trim());
                driverArray(getActivity());
                return true;

        }
        return super.onContextItemSelected(item);
    }


}
