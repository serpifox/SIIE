package com.insidedeveloper.siie;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class menu_salas extends AppCompatActivity {
    CardView materia,chat,maestro,alumnos,tareas;

String nam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_salas);
        Bundle bundle = getIntent().getExtras();
        materia = (CardView) findViewById(R.id.CWMaterias);
        maestro = (CardView) findViewById(R.id.maestros);
        alumnos = (CardView) findViewById(R.id.alumnos);
        tareas=(CardView) findViewById(R.id.tareas);
        nam = bundle.getString("nombre");


        materia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chat= new Intent(menu_salas.this,sala_historia.class);
                chat.putExtra("Nombre",nam);
                startActivity(chat);
            }
        });
        maestro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent maestro= new Intent(menu_salas.this,sala_avisos.class);
                maestro.putExtra("Nombre",nam);
                startActivity(maestro);

            }
        });
        alumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent maestro= new Intent(menu_salas.this,sala_historia.class);
                maestro.putExtra("Nombre",nam);
                startActivity(maestro);

            }
        });
        tareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent maestro= new Intent(menu_salas.this,sala_espanol.class);
                maestro.putExtra("Nombre",nam);
                startActivity(maestro);

            }
        });







    }

}
