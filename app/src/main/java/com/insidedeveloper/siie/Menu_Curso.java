package com.insidedeveloper.siie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Menu_Curso extends AppCompatActivity {
CardView mos,reg,mod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__curso);
        reg=(CardView) findViewById(R.id.registrar);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tareas= new Intent(Menu_Curso.this,Registro_Curso.class);
                startActivity(tareas);

            }
        });
    }
}
