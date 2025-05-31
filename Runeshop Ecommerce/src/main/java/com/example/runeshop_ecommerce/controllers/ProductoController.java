package com.example.runeshop_ecommerce.controllers;


import com.example.runeshop_ecommerce.DTOs.CrearDetalleDTO;
import com.example.runeshop_ecommerce.DTOs.CrearProductoDTO;
import com.example.runeshop_ecommerce.DTOs.GetProductoFilterDTO;
import com.example.runeshop_ecommerce.entities.Detalle;
import com.example.runeshop_ecommerce.entities.Producto;
import com.example.runeshop_ecommerce.entities.enums.Marca;
import com.example.runeshop_ecommerce.entities.enums.TipoProducto;
import com.example.runeshop_ecommerce.services.DetalleService;
import com.example.runeshop_ecommerce.services.ProductoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/paginado")
    public ResponseEntity<Page<Producto>> getProductoPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) throws Exception {
        try {
            Pageable pageRequest = PageRequest.of(page, size);

            Page<Producto> productoPage = productoService.getProductoPaginado(pageRequest);

            if (productoPage.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(productoPage);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
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
    public ResponseEntity<Page<Producto>> filtroProducto(
            @ModelAttribute GetProductoFilterDTO prod,
            @RequestParam(defaultValue = "asc") String orden,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) throws Exception {
        try {
            Pageable pageable = PageRequest.of(page, size);

            String modeloNormalized = (prod.getModelo() != null)
                    ? prod.getModelo().toUpperCase(Locale.ROOT)
                    : null;

            Page<Producto> productos = productoService.filtroProd(
                    prod.getSexo(),
                    prod.getMarca(),
                    prod.getTalleNumero(),
                    prod.getTipoProducto(),
                    modeloNormalized,
                    prod.getCategoria(),
                    prod.getMin(),
                    prod.getMax(),
                    pageable,
                    orden);
            if (productos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}
