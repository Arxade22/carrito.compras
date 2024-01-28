package com.challenge.carrito.compras.repository;

import com.challenge.carrito.compras.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    @Query("SELECT dv FROM DetalleVenta dv WHERE dv.venta.id = :idVenta")
    List<DetalleVenta> findByVentaId(@Param("idVenta") Long idVenta);
}

