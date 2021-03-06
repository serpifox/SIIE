package com.insidedeveloper.siie;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Modificar_Materia extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    GestureDetector gestureDetector;
    EditText etnrc,etnombre;
    Button btnmodificar, btneliminar;
    //String nombre;
    String maloso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar__materia);

        this.gestureDetector = new GestureDetector(this, (GestureDetector.OnGestureListener) this);
        gestureDetector.setOnDoubleTapListener((GestureDetector.OnDoubleTapListener) this);

        etnrc = findViewById(R.id.etNRC);
        btnmodificar = findViewById(R.id.btnModificar);
        btneliminar = findViewById(R.id.btnEliminar);
        etnombre = findViewById(R.id.etNombre);
        Bundle bundle = getIntent().getExtras();
        maloso=bundle.getString("nombre");

        new Modificar_Materia.Consulta_Materia().execute("http://10.0.2.2/siie/Buscar_Materia.php?nombre="+maloso);
        etnrc.setEnabled(false);


        btnmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etnombre.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "No se permiten campos vacios", Toast.LENGTH_LONG).show();
                }else {
                    new ModificarMateria().execute("http://10.0.2.2/siie/Modificar_Materia.php?nrc="+etnrc.getText().toString()+
                            "&nombres="+etnombre.getText().toString());
                    etnombre.setText("");
                    etnrc.setText("");
                }
            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new EliminarMateria().execute("http://10.0.2.2/siie/Eliminar_Materia.php?nrc="+etnrc.getText().toString());
                etnombre.setText("");
                etnrc.setText("");
            }
        });
    }

    private class ModificarMateria extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadUrl(urls[0]);
            }catch (IOException e){
                return "No se puede recuperar la página web URL puede ser válido...";
            }
        }

        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getApplicationContext(), "Se modificaron los datos correctamente", Toast.LENGTH_LONG).show();
            Intent consulta = new Intent(Modificar_Materia.this,mostrar.class);
            startActivity(consulta);
            finish();
        }
    }

    private class EliminarMateria extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadUrl(urls[0]);
            }catch (IOException e){
                return "No se puede recuperar la página web URL puede ser válido...";
            }
        }


        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getApplicationContext(), "Se eliminaron los datos correctamente", Toast.LENGTH_LONG).show();

        }
    }
    private class Consulta_Materia extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadUrl(urls[0]);
            }catch (IOException e){
                return "No se puede recuperar la página web URL puede ser válido...";
            }
        }

        @Override
        protected void onPostExecute(String result) {

            JSONArray ja = null;
            try {

                ja = new JSONArray(result);
                if(ja.length()>0){
                    etnrc.setText(ja.getString(0));
                    etnombre.setText(ja.getString(1));
                }
                else {
                    etnombre.setText("");
                    etnrc.setText("");
                    Toast.makeText(getApplicationContext(),"Registro no encontrado",Toast.LENGTH_LONG).show();
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }

    /* Dado un URL, establece un conexion HttpURLConnection y respuesta
       El contenido de la página web lo crea un InputStream, que se vuelve
     una cadena.*/
    private String downloadUrl(String myurl) throws IOException {
        Log.i("URL",""+myurl);
        myurl = myurl.replace(" ","%20");
        InputStream is = null;
        // Mostrar sólo los primeros 500 caracteres del
        // contenido de la página web.
        int len = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Inicia la consulta
            conn.connect();
            //EL metodo getResponseCode devuelve un codigo de estado,
            // donde un codigo 200 significa exito en la conexion
            int response = conn.getResponseCode();
            Log.d("respuesta", "The response is: " + response);
            is = conn.getInputStream();

            // Convertir el InputStream en una cadena
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Asegurarnos de que el InputStream se cierra después de que la aplicación ha
            // terminado de utilizarlo.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    // Lee un InputStream y lo convierte en una cadena.
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
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
        Intent intentMat = new Intent(Modificar_Materia.this,Menu_Administrador.class);
        startActivity(intentMat);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        finish();
        return false;
    }

    }