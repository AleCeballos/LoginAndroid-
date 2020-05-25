package com.example.pc.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {


    BaseHelper db;
    EditText campoEmail,campoPassword,campoRepassword;
    Button buttonLogin,buttonRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new BaseHelper(this);
        campoEmail =(EditText) findViewById(R.id.campoEmail);
        campoPassword =(EditText) findViewById(R.id.campoPassword);
        campoRepassword =(EditText) findViewById(R.id.campoRepassword);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonRegistrar = (Button) findViewById(R.id.buttonRegistrar);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,Login.class);
                        startActivity(i);
            }
        });
      /***********************************/

      buttonRegistrar.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String EmailReg = campoEmail.getText().toString();
              String PasswordReg = campoPassword.getText().toString();
              String RepassReg = campoRepassword.getText().toString();

              if(EmailReg.equals("")||PasswordReg.equals("")||RepassReg.equals("")){
                  Toast.makeText(getApplicationContext(),"Los campos estan vacios",Toast.LENGTH_SHORT).show();
              }else {

                  if (PasswordReg.equals(RepassReg)){

                      Boolean ckemail = db.ckemail(EmailReg);
                      if(ckemail==true){
                          Boolean insertar = db.insertar(EmailReg,PasswordReg);{

                              if(insertar==true){
                                  Toast.makeText(getApplicationContext(),"Registro exitoso",Toast.LENGTH_SHORT).show();
                              }
                          }
                      }else{
                          Toast.makeText(getApplicationContext(),"El email ya existe",Toast.LENGTH_SHORT).show();

                      }
                  }
                  Toast.makeText(getApplicationContext(),"Las contraseñas no cohinciden",Toast.LENGTH_SHORT).show();
              }
          }
      });



    }





}
