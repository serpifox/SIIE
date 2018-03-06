package com.insidedeveloper.siie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;


public class Registro_Maestro extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    GestureDetector gestureDetector;
    EditText  etnombre,etpaterno,etmaterno;

    public void siguiente(View view){

        Intent materia= new Intent(Registro_Maestro.this,Registro_Maestro2.class);
        materia.putExtra("nombre",etnombre.getText().toString());
        materia.putExtra("paterno",etpaterno.getText().toString());
        materia.putExtra("materno",etmaterno.getText().toString());
        startActivity(materia);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_maestro);
        this.gestureDetector = new GestureDetector(this, (GestureDetector.OnGestureListener) this);
        gestureDetector.setOnDoubleTapListener((GestureDetector.OnDoubleTapListener) this);

        etnombre = findViewById(R.id.etNombre);
        etpaterno = findViewById(R.id.etPaterno);
        etmaterno = findViewById(R.id.etMaterno);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {

        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {


        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Intent intentMeMaestro = new Intent(Registro_Maestro.this,menu_maestro.class);
        startActivity(intentMeMaestro);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        finish();
        return false;
    }
}
