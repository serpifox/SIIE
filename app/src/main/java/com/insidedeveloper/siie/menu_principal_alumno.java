package com.insidedeveloper.siie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class menu_principal_alumno extends AppCompatActivity {
CardView tarea;
String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_alumno);
        Bundle bundle = getIntent().getExtras();
         final String nombre = bundle.getString("usu");
        tarea= (CardView) findViewById(R.id.chat);
        tarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent salas= new Intent(menu_principal_alumno.this,menu_salas.class);
                salas.putExtra("Nombre",nombre);
                startActivity(salas);
            }
        });
    }
}
