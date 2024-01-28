package com.challenge.carrito.compras.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private String antecedentes;

    @Column
    private String telefono;

    @Column
    private String email;

    @OneToMany(mappedBy = "cliente")
    private List<Venta> ventas;

    public Cliente() {
    }

    public Cliente(Long id, String nombres, String apellidos, String antecedentes, String telefono, String email, List<Venta> ventas) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.antecedentes = antecedentes;
        this.telefono = telefono;
        this.email = email;
        this.ventas = ventas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }
}

