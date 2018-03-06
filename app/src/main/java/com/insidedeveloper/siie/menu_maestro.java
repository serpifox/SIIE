package com.insidedeveloper.siie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class menu_maestro extends AppCompatActivity {
    CardView rgMateria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_maestro);
        rgMateria = (CardView) findViewById(R.id.registrar);
        rgMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent materia = new Intent(menu_maestro.this, Registro_Maestro.class);
                startActivity(materia);
            }

});
    }}