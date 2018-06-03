package com.example.progmobile.startruck.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.progmobile.startruck.R;
import com.example.progmobile.startruck.controller.EnterpriseController;
import com.example.progmobile.startruck.model.bean.Enterprise;

public class EnterpriseRegister extends AppCompatActivity {
    EnterpriseController ec;
    EditText edtName, edtCNPJ, edtAdress, edtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_register);
        getSupportActionBar().setTitle("Cadastrar Empresa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtName = findViewById(R.id.edtEnterpriseName);
        edtCNPJ = findViewById(R.id.edtCPNJ);
        edtAdress = findViewById(R.id.edtAdress);
        edtPhone = findViewById(R.id.edtPhone);
    }

    public void onClickSaveEnterprise(View v){
        ec = new EnterpriseController();

        String name = edtName.getText().toString();
        String cnpj = edtCNPJ.getText().toString();
        String adress = edtAdress.getText().toString();
        String phone = edtPhone.getText().toString();

        if(name.trim().isEmpty() || cnpj.trim().isEmpty() || adress.trim().isEmpty() || phone.trim().isEmpty()){
            Toast toast = Toast.makeText(EnterpriseRegister.this,"Por favor, preencha todos os campos!", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            ec.save(this, name, cnpj, adress, phone);
            Toast toast = Toast.makeText(EnterpriseRegister.this,"Sua empresa foi cadastrada com sucesso!",
                    Toast.LENGTH_SHORT);
            toast.show();
            clear();
            finish();
        }
    }

    private void clear() {
        edtName.setText("");
        edtCNPJ.setText("");
        edtAdress.setText("");
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
