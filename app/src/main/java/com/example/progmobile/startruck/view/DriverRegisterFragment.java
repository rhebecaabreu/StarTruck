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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.progmobile.startruck.R;
import com.example.progmobile.startruck.controller.DriverController;
import com.example.progmobile.startruck.model.bean.Driver;
import com.example.progmobile.startruck.model.dao.DriverDAO;

public class DriverRegisterFragment extends Fragment {

    View driversRegisterView;

    private EditText edtDriverName;
    private EditText edtRG;
    private EditText edtCPF;
    private EditText edtPhone;
    private EditText edtEmail;
    private EditText edtNumberCNH;
    private Spinner cat;

    private String driverSelected;

    public String getDriverSelected() {
        return driverSelected;
    }

    public void setDriverSelected(String driver) {
        this.driverSelected = driver;
    }

    String category;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        driversRegisterView = inflater.inflate(R.layout.activity_driver_register, container, false);

        edtDriverName = driversRegisterView.findViewById(R.id.edtDriverName);
        edtRG = driversRegisterView.findViewById(R.id.edtRG);
        edtCPF = driversRegisterView.findViewById(R.id.edtCPF);
        edtPhone = driversRegisterView.findViewById(R.id.edtPhone);
        edtEmail = driversRegisterView.findViewById(R.id.edtEmail);
        edtNumberCNH = driversRegisterView.findViewById(R.id.edtRegNumber);
        cat = driversRegisterView.findViewById(R.id.spinnerCat);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinnerCat, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cat.setAdapter(adapter);

        cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category =cat.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });


        if(DriversListFragment.EDIT_DRIVER==true){
            DriverDAO ddao = new DriverDAO(getActivity());
            System.out.println("hi");
            for(Driver d : ddao.selectDriversByName(MainActivity.usr, DriversListFragment.driverSelected)){
                edtDriverName.setText(d.getName());
                edtRG.setText(d.getRG());

                edtCPF.setText(d.getCPF());
                edtPhone.setText(d.getPhone());
                edtEmail.setText(d.getEmail());
                edtNumberCNH.setText(String.valueOf(d.getCNHnumber()));

                if(d.getCatCnh().equals("A")){
                    cat.setSelection(1);
                }
                if(d.getCatCnh().equals("AB")){
                    cat.setSelection(3);
                }
                if(d.getCatCnh().equals("B")){
                    cat.setSelection(2);
                }
                if(d.getCatCnh().equals("C")){
                    cat.setSelection(4);
                }
                if(d.getCatCnh().equals("D")){
                    cat.setSelection(5);
                }
                if(d.getCatCnh().equals("E")){
                    cat.setSelection(6);
                }
            }

        }


        Button saveDriver = driversRegisterView.findViewById(R.id.btnSaveDriver);
        saveDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DriverController driver = new DriverController();

                String Name = edtDriverName.getText().toString();
                String RG = edtRG.getText().toString();
                String CPF = edtCPF.getText().toString();
                String email = edtEmail.getText().toString();
                String phone = edtPhone.getText().toString();
                String numberCNH = edtNumberCNH.getText().toString();

                if(Name.trim().isEmpty() ||
                        category.equals("Selecione") || numberCNH.trim().isEmpty()||
                        RG.trim().isEmpty() || email.trim().isEmpty() ||
                        CPF.trim().isEmpty() || phone.trim().isEmpty()){
                    Toast.makeText(getActivity(),"Por favor, preencha todos os campos!",Toast.LENGTH_SHORT).show();

                }else {
                    if(DriversListFragment.EDIT_DRIVER==true){
                        driver.update(getActivity(),Name, RG, CPF, email, phone, category, Integer.parseInt(numberCNH));
                    }else {
                        driver.save(getActivity(),Name, RG, CPF, email, phone, category, Integer.parseInt(numberCNH));
                        Toast.makeText(getActivity(),"Cadastro realizado com sucesso",Toast.LENGTH_SHORT).show();
                        clear();
                    }

                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment, new DriversListFragment()).commit();

                }
            }
        });

        return driversRegisterView;
    }


    public void clear(){
        edtDriverName.setText("");
        edtRG.setText("");
        edtCPF.setText("");
        edtEmail.setText("");
        edtPhone.setText("");
        edtNumberCNH.setText("");

    }
}
