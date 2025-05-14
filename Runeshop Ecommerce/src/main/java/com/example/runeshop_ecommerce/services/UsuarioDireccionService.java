package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.Direccion;
import com.example.runeshop_ecommerce.entities.UsuarioDireccion;
import com.example.runeshop_ecommerce.repositories.UsuarioDireccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioDireccionService extends BaseService<UsuarioDireccion, Long> {

    public UsuarioDireccionService(UsuarioDireccionRepository usuarioDireccionRepository) {
        super(usuarioDireccionRepository);
    }
}
