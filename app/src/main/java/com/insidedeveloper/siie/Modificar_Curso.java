package com.insidedeveloper.siie;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class Modificar_Curso extends AppCompatActivity {

    EditText etclave, etinicio, etfinal, etempleado, etnrc;
    Button btnmodificar;
    String clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar__curso);

        etclave = findViewById(R.id.etClave);
        etinicio = findViewById(R.id.etInicio);
        etfinal = findViewById(R.id.etFin);
        etempleado = findViewById(R.id.etEmpleado);
        etnrc = findViewById(R.id.etNRC);
        btnmodificar = findViewById(R.id.btnModificar);
        Bundle bundle = getIntent().getExtras();
        clave = bundle.getString("clave");

        new Modificar_Curso.Consulta_Curso().execute("http://10.0.2.2/siie/Buscar_Curso.php?clave="+clave);

        btnmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etclave.getText().toString().isEmpty() || etinicio.getText().toString().isEmpty() || etfinal.getText().toString().isEmpty() ||
                        etempleado.getText().toString().isEmpty() || etnrc.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "No se permiten campos vacios", Toast.LENGTH_LONG).show();
                }else {
                    new Modificar_Curso.Modificar().execute("http://10.0.2.2/siie/Modificar_Curso.php?clave="+etclave.getText().toString()+
                            "&fechaini="+etinicio.getText().toString()+"&fechafin="+etfinal.getText().toString()+
                            "&idemp="+etempleado.getText().toString()+"&idmat="+etnrc.getText().toString());
                    etclave.setText("");
                    etempleado.setText("");
                    etnrc.setText("");
                    etinicio.setText("");
                    etfinal.setText("");
                }
            }
        });
    }

    private class Consulta_Curso extends AsyncTask <String, Void, String> {

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
                if(ja.length()>0) {
                    etclave.setText(ja.getString(0));
                    etempleado.setText(ja.getString(1));
                    etnrc.setText(ja.getString(2));
                    etinicio.setText(ja.getString(3));
                    etfinal.setText(ja.getString(4));
                }
                else {
                    etclave.setText("");
                    etempleado.setText("");
                    etnrc.setText("");
                    etinicio.setText("");
                    etfinal.setText("");
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class Modificar extends AsyncTask<String, Void, String> {

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
            Intent consultacurso = new Intent(Modificar_Curso.this,Consultar_Cursos.class);
            startActivity(consultacurso);
            finish();
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
}
