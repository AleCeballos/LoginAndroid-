package com.example.pc.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoNotas extends AppCompatActivity {


    ListView listView;
    ArrayList<String> listado,segundoListado;
    BaseHelper db;


    //SOBREESCRIBO EL METODO RECARGANDO LA PANTALLA PARA VER LOS CAMBIOS DE MODIFICAR
    @Override
    protected void onPostResume() {
        super.onPostResume();
        CargarListado();
        CargarSegundoListado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_notas);


        listView =(ListView)findViewById(R.id.listView);
        db = new BaseHelper(this);

        CargarListado();
        CargarSegundoListado();



        //REDIRECCIONO HACIA EL CONTENIDO DE LA NOTA
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //veo posicion Toast.makeText(Listado.this,"Posicion:"+position,Toast.LENGTH_SHORT).show();
                //Toast.makeText(ListadoNotas.this,listado.get(position),Toast.LENGTH_SHORT).show();

                //Indico caracter que separa
                int clave = Integer.parseInt(listado.get(position).split(" ")[0]);

                //ahora me voy a llevar los datos a modificar
                String titulo=segundoListado.get(position).split(" ")[1];
                String contenido=listado.get(position).split(" ")[2];
                Intent intent = new Intent(ListadoNotas.this,Modificar.class);
                intent.putExtra("Id",clave);
                intent.putExtra("Titulo",titulo);
                intent.putExtra("Contenido",contenido);
                startActivity(intent);

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



    public void CargarListado(){

        listado = db.Listado();
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listado);
       // listView.setAdapter(adapter);

    }

    public void CargarSegundoListado(){

        segundoListado = db.segundoListado();
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,R.layout.list_item_mostrar,segundoListado);
        listView.setAdapter(adapter);

    }

}
