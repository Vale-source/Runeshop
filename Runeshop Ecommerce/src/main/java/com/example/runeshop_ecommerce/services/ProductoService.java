package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.Producto;
import com.example.runeshop_ecommerce.repositories.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductoService extends BaseService<Producto, Long> {

    public ProductoService(ProductoRepository productoRepository){
        super(productoRepository);
    }
}
