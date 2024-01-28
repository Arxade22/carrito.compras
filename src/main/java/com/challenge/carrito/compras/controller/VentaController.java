package com.challenge.carrito.compras.controller;

import com.challenge.carrito.compras.model.Venta;
import com.challenge.carrito.compras.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ventas")
//@Api(tags = "Ventas", description = "Operaciones relacionadas con ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    public void registrarVenta(@RequestBody Venta venta) {
        ventaService.registrarVenta(venta);
    }

    @GetMapping("/fecha/{fecha}")
    public List<Venta> consultarVentasPorFecha(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
        return ventaService.consultarVentasPorFecha(fecha);
    }

    @GetMapping("/{id}")
    public Venta consultarVentaPorId(@PathVariable Long id) {
        return ventaService.consultarVentaPorId(id);
    }

    // Otros métodos según tus requerimientos
}

