package com.example.runeshop_ecommerce.repositories;

import com.example.runeshop_ecommerce.entities.Producto;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends BaseRepository<Producto, Long> {
}
