package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.OrdenCompra;
import com.example.runeshop_ecommerce.services.BaseService;
import com.example.runeshop_ecommerce.services.OrdenCompraService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orden-compra")
public class OrdenCompraController extends BaseController<OrdenCompra, Long> {
    public OrdenCompraController(OrdenCompraService ordenCompraService) {
        super(ordenCompraService);
    }
}
