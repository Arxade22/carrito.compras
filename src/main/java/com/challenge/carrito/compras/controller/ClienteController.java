package com.challenge.carrito.compras.controller;

import com.challenge.carrito.compras.model.Cliente;
import com.challenge.carrito.compras.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
//@Api(tags = "Clientes", description = "Operaciones relacionadas con Clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public Cliente registrarCliente(@RequestBody Cliente cliente) {
        return clienteService.registrarCliente(cliente);
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    // Otros métodos según tus requerimientos
}

