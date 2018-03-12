package com.insidedeveloper.siie;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class Inicio_Sesion extends AppCompatActivity {

    EditText edtusuario, edtcontrasenia;
    Button btnMateria;
    String tipo, puesto;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        btnMateria =(Button) findViewById(R.id.btnMateria);
        edtusuario = findViewById(R.id.edtUsuario);
        edtcontrasenia = findViewById(R.id.edtContrasenia);
        pb=findViewById(R.id.progreso);

        btnMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"usu="+edtusuario.getText().toString()+"&contra"+edtcontrasenia.getText().toString(),Toast.LENGTH_LONG).show();
                new Verificar_Usuario().execute("http://10.0.2.2/siie/Inicio_Sesion.php?usu="+edtusuario.getText().toString()+
                        "&contra="+edtcontrasenia.getText().toString());
                pb.setVisibility(View.VISIBLE);

                //Intent intentMat = new Intent(Inicio_Sesion.this,Menu_Administrador.class);
                //Inicio_Sesion.this.startActivity(intentMat);
            }
        });
    }

    private class Verificar_Usuario extends AsyncTask<String, Void, String> {
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
            //Toast.makeText(getApplicationContext(),""+result,Toast.LENGTH_LONG).show();
            try{
                ja = new JSONArray(result);
              //  Toast.makeText(getApplicationContext(),""+ja,Toast.LENGTH_LONG).show();
                if(ja.length()>0){
                    tipo = ja.getString(0);

                    if("Alumno".equals(tipo)){
                        Intent intentAlu = new Intent(Inicio_Sesion.this,menu_principal_alumno.class);
                        intentAlu.putExtra("usu",edtusuario.getText().toString());
                        intentAlu.putExtra("contra",edtcontrasenia.getText().toString());
                        startActivity(intentAlu);
                        Toast.makeText(getApplicationContext(),"Menu Alumno",Toast.LENGTH_LONG).show();
                        pb.setVisibility(View.INVISIBLE);
                    }
                    else if("Empleado".equals(tipo)){
                        puesto = ja.getString(1);
                        if("Administrador".equals(puesto)){
                            Intent intentEmp = new Intent(Inicio_Sesion.this,Menu_Administrador.class);
                            intentEmp.putExtra("usu",edtusuario.getText().toString());
                            intentEmp.putExtra("contra",edtcontrasenia.getText().toString());
                            startActivity(intentEmp);
                            Toast.makeText(getApplicationContext(),"Menu Administrador",Toast.LENGTH_LONG).show();
                            pb.setVisibility(View.INVISIBLE);
                        }
                        else{
                            Intent intentEmp = new Intent(Inicio_Sesion.this,Menu_Administrador.class);
                            intentEmp.putExtra("usu",edtusuario.getText().toString());
                            intentEmp.putExtra("contra",edtcontrasenia.getText().toString());
                            startActivity(intentEmp);
                           /* Intent intentMa = new Intent(Inicio_Sesion.this,menu_principal_maestro.class);
                            intentMa.putExtra("usu",edtusuario.getText().toString());
                            intentMa.putExtra("contra",edtcontrasenia.getText().toString());
                            Inicio_Sesion.this.startActivity(intentMa);*/
                            pb.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(),"Menu Maestro",Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Registro no encontrado",Toast.LENGTH_LONG).show();
                    pb.setVisibility(View.INVISIBLE);
                }
            }catch(JSONException e){
                e.printStackTrace();
            }
            //Toast.makeText(getApplicationContext(), "Inicio de sesión correcto", Toast.LENGTH_LONG).show();
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
