package com.insidedeveloper.siie;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MostarAlumno extends AppCompatActivity {
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostar_alumno);
        setContentView(R.layout.activity_consultar_maestro);
        // new Consulta_Maestro().execute("http://192.168.0.10/siie/Consulta_Maestro.php");
        new MostarAlumno.Consulta_Alumno().execute("http://10.0.2.2/siie/Consulta_Alumno.php");
        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private class Consulta_Alumno extends AsyncTask<String, Void, String> {

        List<Alumno> items = new ArrayList<>();

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
            String nombre,paterno,materno,numemp,puesto,email;

            try {
                JSONObject objson=new JSONObject(result);
                ja = objson.getJSONArray("Alumno");

                for(int i=0;i<=ja.length();i++){
                    JSONObject jsa =ja.getJSONObject(i);
                    nombre = jsa.getString("nombre");
                    paterno = jsa.getString("paterno");
                    materno = jsa.getString("materno");
                    numemp = jsa.getString("matricula");
                    email = jsa.getString("email");
                    //agregamos los datos al arraylist
                    items.add(new Alumno(nombre,paterno,materno,numemp,email));
                    //madams el arraylist al adapter
                    adapter = new AdapterAlumno(items);
                    //el asiganos al recicler la tarjeta que no retorna el adapter
                    recycler.setAdapter(adapter);


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
}
