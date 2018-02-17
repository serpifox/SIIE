package com.insidedeveloper.siie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class activity_menu_materia extends AppCompatActivity {

    CardView rgMateria,rgModificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_materia);

        rgMateria = (CardView) findViewById(R.id.registrar);
        rgModificar = findViewById(R.id.modificar);

        rgMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent materia= new Intent(activity_menu_materia.this,RegistriMateria.class);
                startActivity(materia);
            }
        });

        rgModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent modificar = new Intent(activity_menu_materia.this,Modificar_Materia.class);
                startActivity(modificar);
            }
        });




            }



    }


