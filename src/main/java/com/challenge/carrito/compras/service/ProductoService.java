package com.challenge.carrito.compras.service;

import com.challenge.carrito.compras.model.Producto;
import com.challenge.carrito.compras.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public Producto registrarProducto(Producto producto) {
        // Verifica si el producto ya existe
        if (!productoRepository.existsByNombre(producto.getNombre())) {
            productoRepository.save(producto);
        } else {
            // Manejar el caso en que el producto ya existe
        }
        return producto;
    }

    public List<Producto> obtenerProductosPaginados(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Producto> productoPage = productoRepository.findAll(pageable);
        return productoPage.getContent();
    }

    public void eliminarProducto(Long idProducto) {
        // Verifica si el producto existe
        if (productoRepository.existsById(idProducto)) {
            productoRepository.deleteById(idProducto);
        } else {
            // Manejar el caso en que el producto no existe
        }
    }
    public List<Producto> listarProductos() {
        // Lógica para obtener la lista de productos desde la base de datos
        return productoRepository.findAll();
    }

}

