package com.example.runeshop_ecommerce.repositories;

import com.example.runeshop_ecommerce.entities.Detalle;

import com.example.runeshop_ecommerce.entities.Producto;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleRepository extends BaseRepository<Detalle, Long> {

}
