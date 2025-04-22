package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.Categoria;
import com.example.runeshop_ecommerce.services.BaseService;
import com.example.runeshop_ecommerce.services.CategoriaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")
public class CategoriaController extends BaseController<Categoria, Long> {

    public CategoriaController(CategoriaService categoriaService) {
        super(categoriaService);
    }
}
