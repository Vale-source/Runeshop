package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.Precio;
import com.example.runeshop_ecommerce.services.BaseService;
import com.example.runeshop_ecommerce.services.PrecioService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/precio")
public class PrecioController extends BaseController<Precio, Long> {

    public PrecioController(PrecioService precioService) {
        super(precioService);
    }
}
