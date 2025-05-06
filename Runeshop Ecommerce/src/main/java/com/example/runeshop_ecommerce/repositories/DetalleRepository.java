package com.example.runeshop_ecommerce.repositories;

import com.example.runeshop_ecommerce.entities.Detalle;

import com.example.runeshop_ecommerce.entities.Producto;

import com.example.runeshop_ecommerce.entities.Talle;
import com.example.runeshop_ecommerce.entities.enums.Marca;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleRepository extends BaseRepository<Detalle, Long> {

    @Query("SELECT DISTINCT d.producto " +
            "FROM Detalle d " +
            "WHERE (:marcaParam IS NULL OR :marcaParam = d.marca) ")
    List<Producto> filtroMarca(
            @Param("marcaParam") Marca marca
            );

    @Query("SELECT DISTINCT d.producto " +
            "FROM Detalle d " +
            "WHERE (:talleParam IS NULL OR :talleParam = d.talle.numero)")
    List<Producto> filtroTalle(
            @Param("talleParam") Talle talle
            );

    @Query("SELECT DISTINCT d.producto " +
            "FROM Detalle d " +
            "WHERE d.precio.precioVenta " +
            "BETWEEN :precio_min AND :precio_max")
    List<Producto> filtroPrecio(
            @Param("min") Double precio_min,
            @Param("max") Double precio_max
            );

}
