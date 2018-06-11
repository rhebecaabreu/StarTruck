package com.example.progmobile.startruck.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.progmobile.startruck.R;
import com.example.progmobile.startruck.controller.DriverController;

public class DriverRegisterFragment extends Fragment {

    View driversRegisterView;
    private EditText edtDriverName;
    private EditText edtRG;
    private EditText edtCPF;
    private EditText edtPhone;
    private EditText edtEmail;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        driversRegisterView = inflater.inflate(R.layout.activity_driver_register, container, false);

        edtDriverName = driversRegisterView.findViewById(R.id.edtDriverName);
        edtRG = driversRegisterView.findViewById(R.id.edtRG);
        edtCPF = driversRegisterView.findViewById(R.id.edtCPF);
        edtPhone = driversRegisterView.findViewById(R.id.edtPhone);
        edtEmail = driversRegisterView.findViewById(R.id.edtEmail);

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

                if(Name.trim().isEmpty() ||
                        RG.trim().isEmpty() || email.trim().isEmpty() ||
                        CPF.trim().isEmpty() || phone.trim().isEmpty()){
                    Toast.makeText(getActivity(),"Por favor, preencha todos os campos!",Toast.LENGTH_SHORT).show();

                }else {
                    driver.save(getActivity(),Name, RG, CPF, email, phone);
                    Toast.makeText(getActivity(),"Cadastro realizado com sucesso",Toast.LENGTH_SHORT).show();
                    clear();
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

    }
}
