package com.challenge.carrito.compras.repository;

import com.challenge.carrito.compras.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByFecha(Date fecha);

}

