package com.challenge.carrito.compras.service;

import com.challenge.carrito.compras.model.Venta;
import com.challenge.carrito.compras.repository.ClienteRepository;
import com.challenge.carrito.compras.repository.ProductoRepository;
import com.challenge.carrito.compras.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public void registrarVenta(Venta venta) {
        // Verifica si el cliente existe
        if (clienteRepository.existsById(venta.getIdCliente())) {
            ventaRepository.save(venta);
        } else {

        }
    }

    public List<Venta> consultarVentasPorFecha(Date fecha) {
        return ventaRepository.findByFecha(fecha);
    }

    public Venta consultarVentaPorId(Long id) {
        // Utiliza el método findById() de tu repositorio para obtener la venta por ID
        return ventaRepository.findById(id).orElse(null);
    }

}

