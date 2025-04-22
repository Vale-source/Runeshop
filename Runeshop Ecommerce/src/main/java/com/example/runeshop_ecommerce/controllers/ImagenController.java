package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.Imagen;
import com.example.runeshop_ecommerce.services.BaseService;
import com.example.runeshop_ecommerce.services.ImagenService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imagen")
public class ImagenController extends BaseController<Imagen, Long> {

    public ImagenController(ImagenService imagenService) {
        super(imagenService);
    }
}
