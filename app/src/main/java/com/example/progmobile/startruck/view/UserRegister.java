package com.example.progmobile.startruck.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.progmobile.startruck.R;
import com.example.progmobile.startruck.controller.UserController;

public class UserRegister extends AppCompatActivity {
    private EditText edtFirstName, edtLastName, edtEmail, edtFunction, edtUsername, edtPasswd, edtPasswd2;
    UserController user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("Cadastrar usuário");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtFunction = findViewById(R.id.edtFunction);
        edtEmail = findViewById(R.id.edtEmail);
        edtUsername = findViewById(R.id.edtUsername);
        edtPasswd = findViewById(R.id.edtPassword);
        edtPasswd2 = findViewById(R.id.edtPassword2);
    }

    public void onClickSaveUser(View v){
        user = new UserController();

        String firstName = edtFirstName.getText().toString();
        String lastName = edtLastName.getText().toString();
        String function = edtFunction.getText().toString();
        String email = edtEmail.getText().toString();
        String username = edtUsername.getText().toString();
        String password = edtPasswd.getText().toString();
        String confPasswd = edtPasswd2.getText().toString();

        if(firstName.trim().isEmpty() || lastName.trim().isEmpty() ||
                function.trim().isEmpty() || email.trim().isEmpty() ||
                username.trim().isEmpty() || password.trim().isEmpty() || confPasswd.trim().isEmpty() ){
            Toast toast = Toast.makeText(UserRegister.this,"Por favor, preencha todos os campos!", Toast.LENGTH_SHORT);
            toast.show();
        }else if(!password.equals(confPasswd)){
            Toast toast = Toast.makeText(UserRegister.this,"As senhas digitadas não conferem. ", Toast.LENGTH_SHORT);
            toast.show();
            edtPasswd.setText("");
            edtPasswd2.setText("");
            edtPasswd.requestFocus();
        }else {
            user.save(this, firstName, lastName, function, email, username, password);
            Toast toast = Toast.makeText(UserRegister.this,"Cadastro realizado com sucesso!",
                    Toast.LENGTH_SHORT);
            toast.show();
            clear();
            finish();
        }
    }

    public void clear(){
        edtFirstName.setText("");
        edtLastName.setText("");
        edtFunction.setText("");
        edtEmail.setText("");
        edtPasswd.setText("");
        edtPasswd2.setText("");
        edtUsername.setText("");
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
