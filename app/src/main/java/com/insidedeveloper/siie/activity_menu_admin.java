package com.insidedeveloper.siie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class activity_menu_admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
    }
    public void materia(View view) {


        Intent materia= new Intent(activity_menu_admin.this,activity_menu_materia.class);
        startActivity(materia);


    }
    public void chat(View view) {


        Intent chat= new Intent(activity_menu_admin.this,chat.class);
        startActivity(chat);


    }

}
