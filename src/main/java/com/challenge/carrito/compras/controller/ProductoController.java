package com.challenge.carrito.compras.controller;

import com.challenge.carrito.compras.model.Producto;
import com.challenge.carrito.compras.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
//@Api(tags = "Productos", description = "Operaciones relacionadas con Productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public Producto registrarProducto(@RequestBody Producto producto) {
        return productoService.registrarProducto(producto);
    }

    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    // Otros métodos según tus requerimientos
}

