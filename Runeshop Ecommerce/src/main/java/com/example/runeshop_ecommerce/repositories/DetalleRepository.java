package com.example.runeshop_ecommerce.repositories;

import com.example.runeshop_ecommerce.entities.Detalle;

import com.example.runeshop_ecommerce.entities.Producto;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleRepository extends BaseRepository<Detalle, Long> {

    @Query("SELECT DISTINCT d.producto " +
            "FROM Detalle d " +
            "WHERE d.precio.precioVenta " +
            "BETWEEN :precio_min AND :precio_max")
    List<Producto> filtroPrecio(
            @Param("min") Double precio_min,
            @Param("max") Double precio_max
            );

    @Query("SELECT d.producto " +
            "FROM Detalle d " +
            "ORDER BY d.precio.precioVenta ASC")
    List<Producto> ordenarPrecioAsc ();

    @Query("SELECT d.producto " +
            "FROM Detalle d " +
            "ORDER BY d.precio.precioVenta DESC")
    List<Producto> ordenarPrecioDesc();
}
