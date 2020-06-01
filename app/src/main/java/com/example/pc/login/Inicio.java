package com.example.pc.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;





public class Inicio extends AppCompatActivity {


    Button buttonCrearNota,buttonVerNotas,buttonCrearRecordatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);


        buttonCrearNota = (Button) findViewById(R.id.buttonCrearNota);
        buttonVerNotas = (Button) findViewById(R.id.buttonVerNotas);
        buttonCrearRecordatorio = (Button) findViewById(R.id.buttonCrearRecordatorio);

        buttonCrearNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Inicio.this,CrearNota.class);
                startActivity(i);
            }
        });


        buttonVerNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i =new Intent(Inicio.this,ListadoNotas.class);

                startActivity(i);

            }
        });


        buttonCrearRecordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i =new Intent(Inicio.this,Alarma.class);

                startActivity(i);

            }
        });

    }
}
