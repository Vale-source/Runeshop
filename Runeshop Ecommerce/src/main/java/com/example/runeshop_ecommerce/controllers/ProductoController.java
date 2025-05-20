package com.example.runeshop_ecommerce.controllers;


import com.example.runeshop_ecommerce.entities.Producto;
import com.example.runeshop_ecommerce.entities.enums.Marca;
import com.example.runeshop_ecommerce.entities.enums.TipoProducto;
import com.example.runeshop_ecommerce.services.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController extends BaseController<Producto, Long> {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        super(productoService);
        this.productoService = productoService;
    }

    @GetMapping("/{sexo}/filtro")
    public ResponseEntity<List<Producto>> filtroProducto(
            @PathVariable String sexo,
            @RequestParam(required = false) Marca marca,
            Integer talleNumero,
            TipoProducto tipoProducto,
            String nombre,
            String cateoria
    ) throws Exception {
        try {
            List<Producto> productos = productoService.filtroProd(sexo, marca, talleNumero, tipoProducto, nombre, cateoria);
            if (productos.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(productos);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @GetMapping("/filtro_precio")
    public ResponseEntity<List<Producto>> filtrarPorPrecio(
            @RequestParam Double min, Double max
    ) throws Exception {
        try {
            List<Producto> productos = productoService.filtrarPorPrecio(min, max);
            if (productos.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(productos);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/ord_asc")
    public ResponseEntity<List<Producto>> ordenarPrecioAscendente() throws Exception {
        try {
            List<Producto> productos = productoService.ordenarPrecioAscendente();
            if (productos.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(productos);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/ord_desc")
    public ResponseEntity<List<Producto>> ordenarPrecioDescendente() throws Exception {
        try {
            List<Producto> productos = productoService.ordenarPrecioDescendente();
            if (productos.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(productos);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
