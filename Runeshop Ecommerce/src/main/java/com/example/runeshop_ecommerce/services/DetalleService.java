package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.Detalle;
import com.example.runeshop_ecommerce.repositories.DetalleRepository;
import org.springframework.stereotype.Service;

@Service
public class DetalleService extends BaseService<Detalle, Long> {

    public DetalleService(DetalleRepository detalleRepository){
        super(detalleRepository);
    }
}
