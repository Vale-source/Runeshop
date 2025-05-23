package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.Direccion;
import com.example.runeshop_ecommerce.entities.UsuarioDireccion;

import com.example.runeshop_ecommerce.services.UsuarioDireccionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfil")
public class UsuarioDireccionController extends BaseController<UsuarioDireccion, Long> {

    private final UsuarioDireccionService usuarioDireccionService;

    public UsuarioDireccionController(UsuarioDireccionService usuarioDireccionService, UsuarioDireccionService usuarioDireccionService1) {
        super(usuarioDireccionService);
        this.usuarioDireccionService = usuarioDireccionService1;
    }

    @GetMapping("/usuarios/{usuarioId}/direcciones")
    public ResponseEntity<List<Direccion>> getDireccionesPorUsuario(
            @PathVariable Long usuarioId
    ) throws Exception {
        try {
            List<Direccion> direcciones = usuarioDireccionService.direcionesPorUsuario(usuarioId);
            if (direcciones.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(direcciones);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/usuarios/{usuarioId}/direcciones")
    public ResponseEntity<?> agregarDireccion(
            @Valid @RequestBody Direccion direccion,
            BindingResult resDireccion,
            @PathVariable Long usuarioId
    ) throws Exception {
        if (resDireccion.hasErrors()) {
            return ResponseEntity.badRequest().body("Campos incorrectos o faltantes");
        }
        try {
            usuarioDireccionService.agregarDireccion(usuarioId, direccion);
            return ResponseEntity.status(HttpStatus.CREATED).body("Direccion creada y asignada correctamente");

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
