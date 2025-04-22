package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.Descuento;
import com.example.runeshop_ecommerce.services.BaseService;
import com.example.runeshop_ecommerce.services.DescuentoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/descuento")
public class DescuentoController extends BaseController<Descuento, Long> {

    public DescuentoController(DescuentoService descuentoService) {
        super(descuentoService);
    }
}
