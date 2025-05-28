package com.example.runeshop_ecommerce.controllers;


import com.example.runeshop_ecommerce.DTOs.CrearDetalleDTO;
import com.example.runeshop_ecommerce.DTOs.CrearProductoDTO;
import com.example.runeshop_ecommerce.entities.Detalle;
import com.example.runeshop_ecommerce.entities.Producto;
import com.example.runeshop_ecommerce.entities.enums.Marca;
import com.example.runeshop_ecommerce.entities.enums.TipoProducto;
import com.example.runeshop_ecommerce.services.DetalleService;
import com.example.runeshop_ecommerce.services.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/producto")
public class ProductoController extends BaseController<Producto, Long> {

    private final ProductoService productoService;
    private final DetalleService detalleService;

    public ProductoController(ProductoService productoService, ProductoService productoService1, DetalleService detalleService) {
        super(productoService);
        this.productoService = productoService1;
        this.detalleService = detalleService;
    }

    @PostMapping( "/crear_producto")
    public ResponseEntity<Detalle> crearProducto (
            @RequestPart(value = "imagen") List<MultipartFile> files,
            @RequestPart("producto") CrearProductoDTO productoDTO,
            @RequestPart("detalle") CrearDetalleDTO detalleDTO
            ) throws Exception {
        try {
            Producto producto = productoService.crearProducto(productoDTO);
            Detalle detalle = detalleService.crearDetalle(files, detalleDTO, producto);

            return ResponseEntity.status(HttpStatus.CREATED).body(detalle);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<Producto>> filtroProducto(
            @RequestParam(required = false) String sexo,
            @RequestParam(required = false) Marca marca,
            @RequestParam(required = false) Integer talleNumero,
            @RequestParam(required = false) TipoProducto tipoProducto,
            @RequestParam(required = false)  String modelo,
            @RequestParam(required = false) String categoria
    ) throws Exception {
        try {
            List<Producto> productos = productoService.filtroProd(sexo, marca, talleNumero, tipoProducto, modelo, categoria);
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
            @RequestParam(value = "min", required = true) Double min,
            @RequestParam(name = "max", required = true) Double max
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
