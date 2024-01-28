package com.challenge.carrito.compras.repository;

import com.challenge.carrito.compras.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByNombreContaining(String nombre);

    boolean existsByNombre(String nombre);


}

