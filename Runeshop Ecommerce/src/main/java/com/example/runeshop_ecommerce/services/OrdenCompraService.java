package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.OrdenCompra;
import com.example.runeshop_ecommerce.repositories.OrdenCompraRepository;
import org.springframework.stereotype.Service;

@Service
public class OrdenCompraService extends BaseService<OrdenCompra, Long> {

    public OrdenCompraService(OrdenCompraRepository ordenCompraRepository){
        super(ordenCompraRepository);
    }
}
