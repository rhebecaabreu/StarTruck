package com.example.progmobile.startruck.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.progmobile.startruck.R;
import com.example.progmobile.startruck.controller.EnterpriseController;
import com.example.progmobile.startruck.controller.UserController;

public class MainActivity extends AppCompatActivity {
    UserController user;
    private EditText edtName;
    private EditText edtPassword;
    public Spinner etpSpinner;

    public static int usr;
    public static String usrname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        edtName = findViewById(R.id.edtNome);
        edtPassword = findViewById(R.id.edtPassword);

        enterpriseSpinner();
    }

    public void onClickRegisterUser(View v){
        Intent it = new Intent(this, UserRegister.class);
        startActivity(it);
    }

    public void onClickLogin(View v){
        user = new UserController();
        if(user.login(MainActivity.this, edtName.getText().toString(), edtPassword.getText().toString())){
            usr = user.searchIdUsr(MainActivity.this, edtName.getText().toString());
            usrname = edtName.getText().toString();
            Intent it = new Intent(this, Main3Activity.class);
            startActivity(it);
        }else {
            Toast toast = Toast.makeText(MainActivity.this,
                    "Nome de usuário ou senha não encontrado!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }

    }


    public void enterpriseSpinner(){
        EnterpriseController ec = new EnterpriseController();
        etpSpinner = findViewById(R.id.enterpriseSpinner);

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, ec.spinnerArrayList(this));
        itemsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etpSpinner.setAdapter(itemsAdapter);


        etpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                if(parent.getItemAtPosition(position).toString().equals("Minha empresa não esta na lista")){
                    Intent itt = new Intent(MainActivity.this, EnterpriseRegister.class);
                    startActivity(itt);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }




}
