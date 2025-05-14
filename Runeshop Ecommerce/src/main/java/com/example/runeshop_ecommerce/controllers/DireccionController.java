package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.Direccion;
import com.example.runeshop_ecommerce.services.BaseService;
import com.example.runeshop_ecommerce.services.DireccionService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/direccion")
public class DireccionController extends BaseController<Direccion, Long> {

    public DireccionController(DireccionService direccionService) {
        super(direccionService);
    }
}
