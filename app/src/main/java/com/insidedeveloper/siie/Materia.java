package com.insidedeveloper.siie;

/**
 * Created by maloso on 11/03/2018.
 */

public class Materia {
    String nombre;
String materia;
public Materia(String nom, String id){

    this.nombre=nom;
    this.materia=id;
}

    public Materia() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
}
