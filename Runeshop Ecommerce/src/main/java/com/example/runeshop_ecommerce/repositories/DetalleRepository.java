package com.example.runeshop_ecommerce.repositories;

import com.example.runeshop_ecommerce.entities.Detalle;

import com.example.runeshop_ecommerce.entities.Producto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleRepository extends BaseRepository<Detalle, Long> {

    @Query("SELECT d FROM Detalle d ORDER BY d.producto.modelo ASC")
    Page<Detalle> getDetallesPaginados(Pageable pageable);
}
