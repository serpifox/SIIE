package com.insidedeveloper.siie;

/**
 * Created by Malos on 16/03/2018.
 */

public class Maestro {
    String Nombre;
    String Amaterno;
    String Apaterno;
    String Email;
    public Maestro(String nom,String mat,String pat,String email){
        this.Nombre=nom;
        this.Amaterno=mat;
        this.Apaterno=pat;
        this.Email=mat;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getAmaterno() {
        return Amaterno;
    }

    public void setAmaterno(String amaterno) {
        Amaterno = amaterno;
    }

    public String getApaterno() {
        return Apaterno;
    }

    public void setApaterno(String apaterno) {
        Apaterno = apaterno;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
