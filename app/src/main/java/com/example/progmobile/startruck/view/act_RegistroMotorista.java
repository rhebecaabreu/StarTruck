package com.example.progmobile.startruck.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.progmobile.startruck.controller.UserController;

public class act_RegistroMotorista extends AppCompatActivity {
    private EditText edtDriverName;
    private EditText edtRG;
    private EditText edtCPF;
    private EditText edtPhone;
    private EditText edtEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act__registro_motorista);

        getSupportActionBar().setTitle("Cadastrar usuário");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtDriverName = findViewById(R.id.edtDriverName);
        edtRG = findViewById(R.id.edtRG);
        edtCPF = findViewById(R.id.edtCPF);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);


    }
    public void onClickSaveUser(View v){
        driver = new DriverController();

        String Name = edtDriverName.getText().toString();
        String RG = edtRG.getText().toString();
        String CPF = edtCPF.getText().toString();
        String email = edtEmail.getText().toString();
        String phone = edtPhone.getText().toString();

        if(Name.trim().isEmpty() ||
                RG.trim().isEmpty() || email.trim().isEmpty() ||
                CPF.trim().isEmpty() || phone.trim().isEmpty()){
            Toast toast = Toast.makeText(UserRegister.this,"Por favor, preencha todos os campos!", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            driver.save(this,Name, RG, CPF, email, phone);
            Toast toast = Toast.makeText(UserRegister.this,"Cadastro realizado com sucesso!",
                    Toast.LENGTH_SHORT);
            toast.show();
            clear();
            finish();
        }
    }

    public void clear(){
        edtDriverName.setText("");
        edtRG.setText("");
        edtCPF.setText("");
        edtEmail.setText("");
        edtPhone.setText("");

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
