package com.challenge.carrito.compras.service;

import com.challenge.carrito.compras.model.Cliente;
import com.challenge.carrito.compras.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void cargarClientesDesdeExcel(List<Cliente> clientes) {
        // Lógica para cargar clientes desde un archivo Excel
        // ...

        // Guardar los clientes en la base de datos
        clienteRepository.saveAll(clientes);
    }

    public Cliente registrarCliente(Cliente cliente) {
        if (cliente.getId() == null && !clienteRepository.existsByEmail(cliente.getEmail())) {
            clienteRepository.save(cliente);
        } else {
            throw new ClienteExistenteException("El cliente con el correo " + cliente.getEmail() + " ya existe.");
        }
        return cliente;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }




    private static class ClienteExistenteException extends RuntimeException {
        public ClienteExistenteException(String message) {
            super(message);
        }
    }
}

