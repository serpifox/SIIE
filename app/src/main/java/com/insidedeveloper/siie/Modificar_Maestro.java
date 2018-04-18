package com.insidedeveloper.siie;

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

public class Modificar_Maestro extends AppCompatActivity {

    EditText etnombre,etpaterno,etmaterno,etnumempleado,etusu,etcontra,etcorreo;
    Button btnmodificar;
    String anombre,apaterno,amaterno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar__maestro);

        etnombre = findViewById(R.id.etNombre);
        etpaterno = findViewById(R.id.etPaterno);
        etmaterno = findViewById(R.id.etMaterno);
        etnumempleado = findViewById(R.id.etNumEmp);
        etusu = findViewById(R.id.etUsuario);
        etcontra = findViewById(R.id.etContra);
        etcorreo = findViewById(R.id.etCorreo);
        btnmodificar = findViewById(R.id.btnModificar);
        Bundle bundle = getIntent().getExtras();
        anombre = bundle.getString("nombre");
        apaterno = bundle.getString("paterno");
        amaterno = bundle.getString("materno");
        final String estatus = "Activo";
        final String tipo = "Empleado";
        final String puesto = "Maestro";

        new Modificar_Maestro.ConsultaMaestro().execute("http://10.0.2.2/siie/Buscar_Usuario.php?nombre="+anombre+"&paterno="+apaterno+
                "&materno="+amaterno);
        etusu.setEnabled(false);
        etnumempleado.setEnabled(false);

        btnmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etnombre.getText().toString().isEmpty() || etpaterno.getText().toString().isEmpty() || etmaterno.getText().toString().isEmpty() ||
                        etcontra.getText().toString().isEmpty() || etcorreo.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "No se permiten campos vacios", Toast.LENGTH_LONG).show();
                }else {
                    new Modificar_Maestro.ModificaMaestro().execute("http://10.0.2.2/siie/Modificar_Maestro.php?nombre="+etnombre.getText().toString()+
                            "&paterno="+etpaterno.getText().toString()+"&materno="+etmaterno.getText().toString()+
                            "&correo="+etcorreo.getText().toString()+"&contra="+etcontra.getText().toString()+"&tipo="+tipo+
                            "&numempleado="+etnumempleado.getText().toString()+"&puesto="+puesto);
                }
            }
        });
    }

    private class ConsultaMaestro extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadUrl(urls[0]);
            }catch (IOException e) {
                return "No se puede recuperar la página web URL puede ser válido...";
            }
        }

        @Override
        protected void onPostExecute(String result) {

            JSONArray ja = null;
            try {
                ja = new JSONArray(result);
                if (ja.length()>0) {
                    etnombre.setText(ja.getString(0));
                    etpaterno.setText(ja.getString(1));
                    etmaterno.setText(ja.getString(2));
                    etnumempleado.setText(ja.getString(3));
                    etcorreo.setText(ja.getString(4));
                    etusu.setText(ja.getString(6));
                    etcontra.setText(ja.getString(7));
                }else {
                    etnombre.setText("");
                    etpaterno.setText("");
                    etmaterno.setText("");
                    etnumempleado.setText("");
                    etcorreo.setText("");
                    etusu.setText("");
                    etcontra.setText("");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class ModificaMaestro extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadUrl(urls[0]);
            }catch (IOException e) {
                return "No se puede recuperar la página web URL puede ser válido...";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), "Se modificaron los datos correctamente", Toast.LENGTH_LONG).show();
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
        int len = 1500;

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
