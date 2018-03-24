package com.insidedeveloper.siie;

/**
 * Created by Malos on 23/03/2018.
 */

public class Tarea {
    String id;
    String nombre;
    String descripcion;
    String fecha;
    public Tarea(String ID ,String nom,String des, String Fecha){
        this.nombre=nom;
        this.id=ID;
        this.descripcion=des;
        this.fecha=Fecha;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
