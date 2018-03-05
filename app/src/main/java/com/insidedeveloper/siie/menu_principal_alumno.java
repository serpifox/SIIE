package com.insidedeveloper.siie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class menu_principal_alumno extends AppCompatActivity {
    CardView tareas,chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_alumno_principal);
        tareas=(CardView) findViewById(R.id.tareas);

        chat = (CardView) findViewById(R.id.chats);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chat= new Intent(menu_principal_alumno.this,menu_salas.class);
                startActivity(chat);
            }
        });
        tareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent maestro= new Intent(menu_principal_alumno.this,menu_tarea.class);
                startActivity(maestro);

            }
        });
    }
}
