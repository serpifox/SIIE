package com.insidedeveloper.siie;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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
import java.util.Date;
import java.util.List;

public class Consultar_Cursos extends AppCompatActivity {

    ListView listanombres;
    Button btnbuscalis;
    EditText nomlis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar__cursos);

        listanombres = findViewById(R.id.etlista);
        nomlis = findViewById(R.id.etnombre);
        btnbuscalis = findViewById(R.id.btnbuscar);

        listanombres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int item = i;
                String itemval = (String) listanombres.getItemAtPosition(i);
                Intent modCurso = new Intent(Consultar_Cursos.this,Modificar_Curso.class);
                String clave = String.valueOf(itemval);
                modCurso.putExtra("clave", clave);
                startActivity(modCurso);
            }
        });
    }

    public void Llenar_Lista (ArrayList list) {

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listanombres.setAdapter(adaptador);
    }

    private class Consulta_Cursos extends AsyncTask<String, Void, String> {

        List<Curso> items = new ArrayList<>();
        ArrayList list = new ArrayList();

        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "No se puede recuperar la página web URL puede ser válido...";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            JSONArray ja = null;
            String clave, id_emp, id_mat, fecha_ini, fecha_fin;

            try {
                JSONObject objson = new JSONObject(result);
                ja = objson.getJSONArray("Curso");

                for (int i = 0; i <= ja.length(); i++) {
                    JSONObject jsa = ja.getJSONObject(i);
                    clave = jsa.getString("Clave");
                    id_emp = jsa.getString("Id_Emp");
                    id_mat = jsa.getString("Id_Mat");
                    fecha_ini = jsa.getString("Fecha_Ini");
                    fecha_fin = jsa.getString("Fecha_Fin");

                    items.add(new Curso(clave,id_emp,id_mat,fecha_ini,fecha_fin));
                    list.add(items.get(i).getClave());
                    Llenar_Lista(list);
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /* Dado un URL, establece un conexion HttpURLConnection y respuesta
       El contenido de la página web lo crea un InputStream, que se vuelve
     una cadena.*/
    private String downloadUrl(String myurl) throws IOException {
        Log.i("URL", "" + myurl);
        myurl = myurl.replace(" ", "%20");
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
