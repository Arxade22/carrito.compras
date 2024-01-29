package com.challenge.carrito.compras.model;

import java.math.BigDecimal;

public class ProductoDTO {

    private String nombre;
    private BigDecimal precio;

    public ProductoDTO() {
    }

    public ProductoDTO(String nombre, BigDecimal precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    // Puedes agregar otros campos según tus necesidades

    @Override
    public String toString() {
        return "ProductoDTO{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
