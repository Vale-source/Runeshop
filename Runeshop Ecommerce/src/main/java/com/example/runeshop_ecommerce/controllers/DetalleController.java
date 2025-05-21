package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.Detalle;
import com.example.runeshop_ecommerce.services.DetalleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/detalle")
public class DetalleController extends BaseController<Detalle, Long> {

    private DetalleService detalleService;

    public DetalleController(DetalleService detalleService) {
        super(detalleService);
    }

    @PutMapping("/actualizarImagenDetalle")
    public ResponseEntity<Detalle> actualizarDetalle(
            @RequestParam("detalleId") Long detalleId,
            @RequestParam("imagenId") Long imagenId,
            @RequestPart("imagen") MultipartFile file
    ) throws Exception {
        try {
            Detalle actualizarDetalle= detalleService.updateDetalle(detalleId, imagenId, file);
            return ResponseEntity.ok(actualizarDetalle);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
