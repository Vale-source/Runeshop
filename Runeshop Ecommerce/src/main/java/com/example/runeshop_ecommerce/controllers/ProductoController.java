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
import java.util.Locale;
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
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Double min,
            @RequestParam(required = false) Double max,
            @RequestParam(required = false) Boolean asc,
            @RequestParam(required = false) Boolean desc
    ) throws Exception {
        try {
            List<Producto> productos = productoService.filtroProd(sexo, marca, talleNumero, tipoProducto, modelo.toUpperCase(Locale.ROOT), categoria, min, max);
            if (productos.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                if (asc == null) {
                    asc = false;
                }

                if (desc == null) {
                    desc = false;
                }

                if (asc) {
                    List<Producto> productosOrdAsc = productoService.orderAsc(productos);
                    return ResponseEntity.ok(productosOrdAsc);
                } else if (desc) {
                    List<Producto> productosOrdDesc = productoService.orderDesc(productos);
                    return ResponseEntity.ok(productosOrdDesc);
                }

                return ResponseEntity.ok(productos);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}
