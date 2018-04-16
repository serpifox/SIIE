package com.insidedeveloper.siie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class menu_principal_maestro extends AppCompatActivity {

    CardView cursos,alumnos,chat,tareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_maestro);

        cursos = findViewById(R.id.Cursos);
        alumnos = findViewById(R.id.Alumnos);
        chat = findViewById(R.id.Chat);
        tareas = findViewById(R.id.Tareas);

        cursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent curso = new Intent(menu_principal_maestro.this,Menu_Curso.class);
                startActivity(curso);
            }
        });

        alumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alu = new Intent(menu_principal_maestro.this,Menu_Alumno.class);
                startActivity(alu);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tarea = new Intent(menu_principal_maestro.this,menu_tarea.class);
                startActivity(tarea);
            }
        });
    }

}