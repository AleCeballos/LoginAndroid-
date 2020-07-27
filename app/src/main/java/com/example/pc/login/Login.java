package com.example.pc.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText campoEmailLogin,campoPasswordLogin;
    Button buttonLoguearme;
    Button buttonVolver;
    BaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new BaseHelper(this);

        campoEmailLogin = (EditText)findViewById(R.id.campoEmailLogin);
        campoPasswordLogin = (EditText)findViewById(R.id.campoPasswordLogin);
        buttonLoguearme = (Button)findViewById(R.id.buttonLoguearme);
        buttonVolver = (Button)findViewById(R.id.buttonVolver);

        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i =new Intent(Login.this,MainActivity.class);
                startActivity(i);

            }
        });

        buttonLoguearme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = campoEmailLogin.getText().toString();
                String password = campoPasswordLogin.getText().toString();
                Boolean ckemailpassword =db.ckemailpass(email,password);
                if(ckemailpassword==true){
                    Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_SHORT).show();
                        Intent i =new Intent(Login.this,Inicio.class);
                           startActivity(i);}
                   else{
                    Toast.makeText(getApplicationContext(),"ERROR EN EMAIL O PASSWORD",Toast.LENGTH_SHORT).show();
                }



            }
        });


        //AGREGO EL BOTON DE REGRESAR
        //----------------------------------------------------------//
        if(getSupportActionBar()!=null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        //----------------------------------------------------------//
    }

    //SI APRETO EL BOTON ATRAS CIERRO EL ACTIVITY
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    //-------------------------------------/
}
