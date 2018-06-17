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







}
