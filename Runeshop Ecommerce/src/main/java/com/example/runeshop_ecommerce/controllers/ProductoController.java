package com.example.runeshop_ecommerce.controllers;


import com.example.runeshop_ecommerce.DTOs.CrearProductoDTO;
import com.example.runeshop_ecommerce.entities.Precio;
import com.example.runeshop_ecommerce.entities.Producto;
import com.example.runeshop_ecommerce.entities.Talle;
import com.example.runeshop_ecommerce.entities.enums.Marca;
import com.example.runeshop_ecommerce.entities.enums.TipoProducto;
import com.example.runeshop_ecommerce.services.DetalleService;
import com.example.runeshop_ecommerce.services.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    private ProductoService productoService;
    private DetalleService detalleService;

    public ProductoController(ProductoService productoService) {
        super(productoService);
    }

    @PostMapping("/crear_producto")
    public ResponseEntity<?> crearProducto (
            @RequestPart("imagen") MultipartFile file,
            @RequestPart("producto")CrearProductoDTO dto
            ) {
        try {
            Producto producto = productoService.crearProducto(dto, file);

            String imagenUrl = producto.getDetalles().get(0).getImagenes().get(0).getImagenUrl();

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Producto creado exitosamente");
            response.put("imagenUrl", imagenUrl);
            response.put("producto", producto);

            System.out.println(">>> Entrando al endpoint de crear producto <<<");
            System.out.println("DTO recibido: " + dto);
            System.out.println("Archivo recibido: " + file.getOriginalFilename());


            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{sexo}/filtro")
    public ResponseEntity<List<Producto>> filtroProducto(
            @PathVariable String sexo,
            @RequestParam(required = false) Marca marca,
            @RequestParam(required = false) Integer talleNumero,
            @RequestParam(required = false) TipoProducto tipoProducto,
            @RequestParam(required = false)  String nombre,
            @RequestParam(required = false) String cateoria
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
