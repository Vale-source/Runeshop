package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.UsuarioDireccion;
import com.example.runeshop_ecommerce.services.BaseService;
import com.example.runeshop_ecommerce.services.UsuarioDireccionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario-direccion")
public class UsuarioDireccionController extends BaseController<UsuarioDireccion, Long> {

    public UsuarioDireccionController(UsuarioDireccionService usuarioDireccionService) {
        super(usuarioDireccionService);
    }
}
