package com.insidedeveloper.siie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class activity_menu_admin extends AppCompatActivity {

    CardView materia,chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
        materia = (CardView) findViewById(R.id.CWMaterias);
        chat = (CardView) findViewById(R.id.chats);
        materia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent materia= new Intent(activity_menu_admin.this,activity_menu_materia.class);
                startActivity(materia);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chat= new Intent(activity_menu_admin.this,chat.class);
                startActivity(chat);
            }
        });



    }

}
