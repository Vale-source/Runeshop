package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.Imagen;
import com.example.runeshop_ecommerce.repositories.ImagenRepository;
import org.springframework.stereotype.Service;

@Service
public class ImagenService extends BaseService<Imagen, Long> {

    public ImagenService(ImagenRepository imagenRepository) {
        super(imagenRepository);
    }
}
