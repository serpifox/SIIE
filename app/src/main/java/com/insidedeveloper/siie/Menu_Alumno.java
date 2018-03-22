package com.insidedeveloper.siie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Menu_Alumno extends AppCompatActivity {

    CardView registro, modificar, consultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_alumno);

        registro = findViewById(R.id.registrar);
        modificar = findViewById(R.id.modificar);
        consultar = findViewById(R.id.consultar);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrar= new Intent(Menu_Alumno.this,Registro_Alumno.class);
                startActivity(registrar);
            }
        });

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Mostar= new Intent(Menu_Alumno.this,MostarAlumno.class);
                startActivity(Mostar);

            }
        });
    }
}
