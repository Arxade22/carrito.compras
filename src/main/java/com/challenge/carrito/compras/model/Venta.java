package com.challenge.carrito.compras.model;

import java.util.Date;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    private Date fecha;

    @OneToMany(mappedBy = "venta")
    private List<DetalleVenta> detallesVentas;

    public Venta() {
    }

    public Venta(Long id, Cliente cliente, Date fecha, List<DetalleVenta> detallesVentas) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.detallesVentas = detallesVentas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<DetalleVenta> getDetallesVentas() {
        return detallesVentas;
    }

    public void setDetallesVentas(List<DetalleVenta> detallesVentas) {
        this.detallesVentas = detallesVentas;
    }

    public Long getIdCliente() {
        return cliente != null ? cliente.getId() : null;
    }
}
