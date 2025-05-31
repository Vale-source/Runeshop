package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.Imagen;
import com.example.runeshop_ecommerce.services.BaseService;
import com.example.runeshop_ecommerce.services.ImagenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/imagen")
public class ImagenController extends BaseController<Imagen, Long> {
    
    private final ImagenService imagenService;
    
    public ImagenController(ImagenService imagenService, ImagenService imagenService1) {
        super(imagenService);
        this.imagenService = imagenService1;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteString(
            @PathVariable Long id
            ) throws Exception {
        try {
            imagenService.borrarImagen(id);
            return ResponseEntity.status(200).body("La imagen se borró con éxito");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
