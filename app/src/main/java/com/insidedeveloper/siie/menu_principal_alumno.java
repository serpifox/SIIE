package com.insidedeveloper.siie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class menu_principal_alumno extends AppCompatActivity {
CardView tarea,chat;
public String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_alumno);
        tarea= (CardView) findViewById(R.id.tareas);
        chat= (CardView) findViewById(R.id.chat);
        Bundle bundles = getIntent().getExtras();
        name = bundles.getString("Usu");
        Toast.makeText(getApplicationContext(),"el nombre es"+name,Toast.LENGTH_LONG).show();

        tarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent salas= new Intent(menu_principal_alumno.this,Cursos.class);
                salas.putExtra("nombre",name);
                startActivity(salas);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent salas= new Intent(menu_principal_alumno.this,menu_salas.class);
                salas.putExtra("nombre",name);
                startActivity(salas);
            }
        });
    }
}
