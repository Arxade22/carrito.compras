package com.challenge.carrito.compras.repository;

import com.challenge.carrito.compras.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Método para buscar clientes por nombre
    List<Cliente> findByNombresContaining(String nombres);

    boolean existsByEmail(String email);


}

