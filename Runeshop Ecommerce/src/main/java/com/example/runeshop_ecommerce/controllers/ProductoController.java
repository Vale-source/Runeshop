package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.Producto;
import com.example.runeshop_ecommerce.services.BaseService;
import com.example.runeshop_ecommerce.services.ProductoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producto")
public class ProductoController extends BaseController<Producto, Long> {

    public ProductoController(ProductoService productoService) {
        super(productoService);
    }
}
