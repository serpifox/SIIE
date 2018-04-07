package com.insidedeveloper.siie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class menu_tarea extends AppCompatActivity {
    CardView mos,reg,mod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tarea);
        reg=(CardView) findViewById(R.id.registrar);
        mod=(CardView) findViewById(R.id.mostrar);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tareas= new Intent(menu_tarea.this,registrar_tarea.class);
                startActivity(tareas);

            }
        });
        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tareas= new Intent(menu_tarea.this,consultar_archivos.class);
                startActivity(tareas);

            }
        });
    }

}
