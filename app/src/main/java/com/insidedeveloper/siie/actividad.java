package com.insidedeveloper.siie;

/**
 * Created by Malos on 04/04/2018.
 */

public class actividad {
    String id;
    String nombre;
    String url;
    public actividad (String ID,String nom,String url){
        this.id=ID;
        this.nombre=nom;
        this.url=url;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
