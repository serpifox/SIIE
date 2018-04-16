package com.insidedeveloper.siie;

import java.util.Date;

public class Curso {

    String clave;
    String id_emp;
    String id_mat;
    String fecha_ini;
    String fecha_fin;

    public Curso (String clave, String id_emp, String id_mat, String fecha_ini, String fecha_fin) {

        this.clave = clave;
        this.id_emp = id_emp;
        this.id_mat = id_mat;
        this.fecha_ini = fecha_ini;
        this.fecha_fin = fecha_fin;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getId_emp() {
        return id_emp;
    }

    public void setId_emp(String id_emp) {
        this.id_emp = id_emp;
    }

    public String getId_mat() {
        return id_mat;
    }

    public void setId_mat(String id_mat) {
        this.id_mat = id_mat;
    }

    public String getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(String fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
}
