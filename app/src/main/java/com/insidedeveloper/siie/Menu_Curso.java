package com.insidedeveloper.siie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Menu_Curso extends AppCompatActivity {

    CardView reg,mod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__curso);

        reg = findViewById(R.id.registrar);
        mod = findViewById(R.id.modificar);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(Menu_Curso.this,Registro_Curso.class);
                startActivity(reg);
            }
        });

        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mod = new Intent(Menu_Curso.this,Consultar_Cursos.class);
                startActivity(mod);
            }
        });
    }
}
