package com.insidedeveloper.siie;

/**
 * Created by Malos on 22/03/2018.
 */

public class Alumno {
    String nombre;
    String paterno;
    String materno;
    String correo;
    String matricula;

    public Alumno(String nom ,String pat, String mat, String cor, String matr){

        this.nombre=nom;
        this.materno=mat;
        this.paterno=pat;
        this.matricula=matr;
        this.correo=cor;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setCorreo(String correo) {
        this.correo = correo;

    }
}
