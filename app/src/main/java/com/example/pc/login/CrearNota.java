package com.example.pc.login;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CrearNota extends AppCompatActivity {


    Button buttonGuardarNota;
    EditText campoTitulo,campoContenido;


    BaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_nota);

        db = new BaseHelper(this);
        buttonGuardarNota = (Button) findViewById(R.id.buttonGuardarNota);
       // buttonAgregarImagen = (Button) findViewById(R.id.buttonAgregarImagen);

        campoTitulo =(EditText) findViewById(R.id.campoTitulo);
        campoContenido =(EditText) findViewById(R.id.campoContenido);





        buttonGuardarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Titulo = campoTitulo.getText().toString();
                String Contenido = campoContenido.getText().toString();

                Boolean insertarNota= db.insertarNota(Titulo,Contenido);

                if(insertarNota==true){
                    Toast.makeText(getApplicationContext(),"Nota guardada",Toast.LENGTH_SHORT).show();



                    onBackPressed();//vuelvo hacia atras

        }else{
            Toast.makeText(getApplicationContext(),"Error al guardar Nota",Toast.LENGTH_SHORT).show();

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


   // public void agregarImagenMetodo(View v){

   //     buttonAgregarImagen.setVisibility(View.INVISIBLE);
  //  }




}
