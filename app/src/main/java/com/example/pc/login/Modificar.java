package com.example.pc.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Modificar extends AppCompatActivity {

    EditText campoEditarTitulo,campoEditarContenido;

     Button buttonGuardarEdicion,buttonEliminarNota;
    BaseHelper db;


    int id;
    String titulo;
    String contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        db = new BaseHelper(this);


        //RECIBO LOS PARAMETROS DESDE LISTADO

        Bundle b = getIntent().getExtras();
        if(b!=null){

            id = b.getInt("Id");
          titulo = b.getString("Titulo");
            contenido = b.getString("Contenido");
        }

        campoEditarTitulo =(EditText) findViewById(R.id.campoEditarTitulo);
        campoEditarContenido =(EditText) findViewById(R.id.campoEditarContenido);

        //SETEO LOS DATOS EN LOS CAMPOS DE TEXTO
        campoEditarTitulo.setText(titulo);
        campoEditarContenido.setText(contenido);



        buttonGuardarEdicion = (Button) findViewById(R.id.buttonGuardarEdicion);
        buttonEliminarNota = (Button) findViewById(R.id.buttonEliminarNota);


        //boton que eliminara al apretar
        buttonEliminarNota.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                db.eliminar(id);
                onBackPressed();//vuelvo hacia atras
            }
        });


        //boton que modificara al apretar
        buttonGuardarEdicion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                db.modificar(id, campoEditarTitulo.getText().toString(),campoEditarContenido.getText().toString());
                onBackPressed();//vuelvo hacia atras
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
