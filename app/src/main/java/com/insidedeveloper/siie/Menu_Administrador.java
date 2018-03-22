package com.insidedeveloper.siie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Menu_Administrador extends AppCompatActivity {

    CardView materia,chat,maestro,alumnos,tareas,cursos;
    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_administrador);
        materia = (CardView) findViewById(R.id.CWMaterias);
        chat = (CardView) findViewById(R.id.chat);
        maestro = (CardView) findViewById(R.id.maestros);
        alumnos = (CardView) findViewById(R.id.alumnos);
        tareas=(CardView) findViewById(R.id.tareas);
        cursos=(CardView) findViewById(R.id.curso);
        Bundle bundle = getIntent().getExtras();
        nombre = bundle.getString("usu");

        materia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent materia= new Intent(Menu_Administrador.this,Menu_Materia.class);
                startActivity(materia);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chat= new Intent(Menu_Administrador.this, chat.class);
                chat.putExtra("Nombre",nombre);
                startActivity(chat);
            }
        });
        maestro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent maestro= new Intent(Menu_Administrador.this,menu_maestro.class);
                startActivity(maestro);

            }
        });
        alumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent alumno= new Intent(Menu_Administrador.this,Menu_Alumno.class);
                startActivity(alumno);

            }
        });
        tareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tareas= new Intent(Menu_Administrador.this,menu_tarea.class);
                startActivity(tareas);

            }
        });


        cursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tareas= new Intent(Menu_Administrador.this,Menu_Curso.class);
                startActivity(tareas);

            }
        });




    }

}
