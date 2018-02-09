package com.insidedeveloper.siie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class activity_menu_materia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_materia);




            }
public void materia(View view){
    Intent intentMat = new Intent(activity_menu_materia.this,RegistriMateria.class);
    activity_menu_materia.this.startActivity(intentMat);
}


    }


