package com.insidedeveloper.siie;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

public class Cursos extends AppCompatActivity {

    ListView listanombres;
    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);

        listanombres = findViewById(R.id.lvnombres);
        Bundle bund = getIntent().getExtras();
        usuario=bund.getString("nombre");

        Toast.makeText(getApplicationContext(),usuario,Toast.LENGTH_LONG).show();
        new Cursos.Consulta_Tareas().execute("http://10.0.2.2/siie/Consulta_Tarea.php");

        listanombres.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                int item = position;

                String itemval = (String) listanombres.getItemAtPosition(position);
                Intent intentAlu = new Intent(Cursos.this,subir_archivo.class);
                String nombre=String.valueOf(itemval);
                intentAlu.putExtra("nombre",nombre);
                intentAlu.putExtra("Id",id);
                intentAlu.putExtra("usuario",usuario);
                startActivity(intentAlu);
            }

        });
    }

    public void llenarLista(ArrayList lis){
        ArrayAdapter adaptador= new ArrayAdapter(this,android.R.layout.simple_list_item_1, lis);
        listanombres.setAdapter(adaptador);
    }
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private class Consulta_Tareas extends AsyncTask<String, Void, String> {
        List<Tarea> items = new ArrayList<>();
        ArrayList lis= new ArrayList();

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
            String nombre,Descripcion,id,fecha;

            try {
                JSONObject objson=new JSONObject(result);
                ja = objson.getJSONArray("Tarea");

                for(int i=0;i<=ja.length();i++){
                    JSONObject jsa =ja.getJSONObject(i);
                    nombre = jsa.getString("nombre");
                    id = jsa.getString("id");
                    Descripcion= jsa.getString("descripcion");
                    fecha = jsa.getString("fecha");
                    items.add(new Tarea(id,nombre,Descripcion,fecha));
                    lis.add(items.get(i).getNombre());
                    llenarLista(lis);
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
