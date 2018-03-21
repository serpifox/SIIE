package com.insidedeveloper.siie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class menu_maestro extends AppCompatActivity {

    CardView rgMaestro, modMaestro, conMaestro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_maestro);

        rgMaestro = findViewById(R.id.registrar);
        conMaestro = findViewById(R.id.consultar);
        modMaestro = findViewById(R.id.modificar);

        rgMaestro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regMaestro = new Intent(menu_maestro.this, Registro_Maestro.class);
                startActivity(regMaestro);
            }
        });

        conMaestro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent conMaestro = new Intent(menu_maestro.this, ConsultarMaestro.class);
                startActivity(conMaestro);
            }
        });
    }
}