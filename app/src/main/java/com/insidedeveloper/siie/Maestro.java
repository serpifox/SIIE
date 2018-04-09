package com.insidedeveloper.siie;

/**
 * Created by Malos on 16/03/2018.
 */

public class Maestro {

    String Nombre;
    String Amaterno;
    String Apaterno;
    String Email;
    String NumEmp;
    String Puesto;

    public Maestro(String nom,String pat,String mat,String emp,String email,String pues){
        this.Nombre = nom;
        this.Apaterno = pat;
        this.Amaterno = mat;
        this.NumEmp = emp;
        this.Email = email;
        this.Puesto = pues;
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

    public String getNumEmp() {
        return NumEmp;
    }

    public void setNumEmp(String numEmp) {
        NumEmp = numEmp;
    }

    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String puesto) {
        Puesto = puesto;
    }
}