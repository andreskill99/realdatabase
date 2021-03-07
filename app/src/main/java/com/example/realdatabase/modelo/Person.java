package com.example.realdatabase.modelo;

public class Person {
    private String uid;
    private String cedula;
    private String nombre;
    private String genero;
    private String provincia;
    private String correo;
    private String Pais;

    public Person(String nombre, String genero, String provincia, String correo, String pais) {

        this.nombre = nombre;
        this.genero = genero;
        this.provincia = provincia;
        this.correo = correo;
        Pais = pais;
    }

    public Person() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }
}



