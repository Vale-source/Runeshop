package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.Producto;
import com.example.runeshop_ecommerce.entities.Talle;
import com.example.runeshop_ecommerce.entities.enums.Marca;
import com.example.runeshop_ecommerce.services.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController extends BaseController<Producto, Long> {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        super(productoService);
        this.productoService = productoService;
    }

    @GetMapping("/marca")
    public ResponseEntity<List<Producto>> filtrarPorMarca(
            @RequestParam(required = false) Marca marca
    ) throws Exception {
        List<Producto> productos = productoService.filtrarPorMarca(marca);
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(productos);
        }
    }

    @GetMapping("/talle")
    public ResponseEntity<List<Producto>> filtrarPorTalle(
            @RequestParam(required = false) Talle talle
    ) throws Exception {
        List<Producto> productos = productoService.filtrarPorTalle(talle);
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(productos);
        }
    }

    @GetMapping("/precio")
    public ResponseEntity<List<Producto>> filtrarPorPrecio(
            @RequestParam(required = false) Double min, Double max
    ) throws Exception {
        List<Producto> productos = productoService.filtrarPorPrecio(min, max);
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(productos);
        }
    }
}
