package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.Detalle;
import com.example.runeshop_ecommerce.services.DetalleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/detalle")
public class DetalleController extends BaseController<Detalle, Long> {

    private final DetalleService detalleService;

    public DetalleController(DetalleService detalleService, DetalleService detalleService1) {
        super(detalleService);
        this.detalleService = detalleService1;
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<Detalle>> getDetallesPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) throws Exception {
        try {
            Pageable pageable = PageRequest.of(page, size);

            Page<Detalle> detallePage = detalleService.getDetallesPaginados(pageable);

            if (detallePage.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok().body(detallePage);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PutMapping("/actualizarImagenDetalle")
    public ResponseEntity<Detalle> actualizarDetalle(
            @RequestPart("detalleId") Long detalleId,
            @RequestPart("imagenId") Long imagenId,
            @RequestPart("imagen") MultipartFile file
    ) throws Exception {
        try {
            Detalle actualizarDetalle = detalleService.updateDetalle(detalleId, imagenId, file);
            return ResponseEntity.ok(actualizarDetalle);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PutMapping("/agregarDescuento")
    public ResponseEntity<Detalle> agregarDescuento(
            @RequestParam("detalleId") Long detalleId,
            @RequestParam("descuentoId") Long descuentoId,
            @RequestParam("finDescuento") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha
            ) throws Exception {
        try {
            Detalle descuento = detalleService.aplicarDescuento(detalleId, descuentoId, fecha);
            return ResponseEntity.status(200).body(descuento);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
