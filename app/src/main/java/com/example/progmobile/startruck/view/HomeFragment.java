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
import com.example.progmobile.startruck.model.dao.VehicleDAO;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    Driver dd;
    String yy;
    View home;
    ListView listEnterprise, listMaintenance, listTravel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        home = inflater.inflate(R.layout.activity_home, container, false);

        listEnterprise = home.findViewById(R.id.listEnterprise);
        listMaintenance = home.findViewById(R.id.listMaintenance);
        listTravel = home.findViewById(R.id.listTravel);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, enterpriseArray(getActivity()));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        listEnterprise.setAdapter(arrayAdapter);
        registerForContextMenu(listEnterprise);

        final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, mArray(getActivity()));
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_list_item_1);
        listMaintenance.setAdapter(arrayAdapter2);
        registerForContextMenu(listMaintenance);

        final ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, tArray(getActivity()));
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_list_item_1);
        listTravel.setAdapter(arrayAdapter3);
        registerForContextMenu(listTravel);

        listEnterprise.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                yy = arrayAdapter.getItem(position);
                VehicleListFragment.setNameVehicle(yy);
                return false;
            }
        });

        listTravel.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                yy = arrayAdapter3.getItem(position);
                VehicleListFragment.setNameVehicle(yy);
                return false;
            }
        });

        listMaintenance.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                yy = arrayAdapter2.getItem(position);
                VehicleListFragment.setNameVehicle(yy);
                return false;
            }
        });

        return home;
    }

    public void onResume(){
        super.onResume();
        //OnResume Fragment

        tArray(getActivity());
        mArray(getActivity());
        enterpriseArray(getActivity());
    }



    public static ArrayList<String> tArray(Context context){
        ArrayList<String> t = new ArrayList<String>();
        VehicleDAO d = new VehicleDAO(context);
        t.clear();
        for(Vehicle dv : d.selectVehiclesByStatusT(MainActivity.usr)) {
            t.add(dv.getNameVehicle());
        }
        return t;
    }


    public static ArrayList<String> enterpriseArray(Context context){
        ArrayList<String> enterprise = new ArrayList<String>();
        VehicleDAO d = new VehicleDAO(context);
        enterprise.clear();

        for(Vehicle dv : d.selectVehiclesByStatusEnterprise(MainActivity.usr)) {
            enterprise.add(dv.getNameVehicle());
        }
        return enterprise;
    }

    public static ArrayList<String> mArray(Context context){
        ArrayList<String> m = new ArrayList<String>();
        VehicleDAO d = new VehicleDAO(context);
        m.clear();

        for(Vehicle dv : d.selectVehiclesByStatusM(MainActivity.usr)) {
            m.add(dv.getNameVehicle());
        }
        return m;
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
                tArray(getActivity());
                mArray(getActivity());
                enterpriseArray(getActivity());


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
