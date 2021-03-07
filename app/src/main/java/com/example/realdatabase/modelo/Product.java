package com.example.realdatabase.modelo;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Product {
    private String codigo;
    private String nombre;
    private String stock;
    private String costo;
    private String venta;



    public Product() {
    }
    public Product(String codigo, String nombre, String stock, String costo, String venta) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.costo = costo;
        this.venta = venta;
    }

    public Product(String codigo, String stock) {
        this.codigo = codigo;
        this.stock = stock;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getVenta() {
        return venta;
    }

    public void setVenta(String venta) {
        this.venta = venta;
    }


}
