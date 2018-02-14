package com.insidedeveloper.siie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InicioSesion extends AppCompatActivity {

    Button btnMateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        btnMateria =(Button) findViewById(R.id.btnMateria);
        btnMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMat = new Intent(InicioSesion.this,activity_menu_admin.class);
                InicioSesion.this.startActivity(intentMat);
            }
        });
    }
}
