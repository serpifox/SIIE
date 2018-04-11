package com.insidedeveloper.siie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class Menu_Materia extends AppCompatActivity {

    CardView rgMateria,rgModificar,rgConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_materia);

        rgMateria = findViewById(R.id.registrar);
        rgModificar = findViewById(R.id.modificar);
        rgConsultar = findViewById(R.id.consultar);

        rgMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent materia= new Intent(Menu_Materia.this,Registro_Materia.class);
                startActivity(materia);
            }
        });

        rgConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent consulta = new Intent(Menu_Materia.this,mostrar.class);
                startActivity(consulta);
            }
        });

    }

}


