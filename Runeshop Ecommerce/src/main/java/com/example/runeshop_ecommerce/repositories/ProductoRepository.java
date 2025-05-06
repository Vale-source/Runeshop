package com.example.runeshop_ecommerce.repositories;

import com.example.runeshop_ecommerce.entities.Producto;
import com.example.runeshop_ecommerce.entities.enums.TipoProducto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends BaseRepository<Producto, Long> {

    @Query("SELECT DISTINCT p.tipoProducto " +
            "FROM Producto p " +
            "WHERE (:tipoProdParam IS NULL OR :tipoProdPara = p.tipoProducto)")
    List<Producto> filtroTipoProducto(
            @Param("tipoProdPara") TipoProducto tipoProducto
    );

    @Query("SELECT DISTINCT p.nombre " +
            "FROM Producto p " +
            "WHERE (:nombreParam IS NULL OR :nombreParam = p.nombre)")
    List<Producto> filtroNombre(
            @Param("nombreParam") Producto prod
    );

    @Query("SELECT DISTINCT p.categoria " +
            "FROM Producto p " +
            "WHERE (:cateogriaParam IS NULL OR :cateogriaParam = p.categoria)")
    List<Producto> filtroCategoria(
            @Param("cateogriaParam") Producto prod
    );

    @Query("SELECT DISTINCT p.sexo " +
            "FROM Producto p " +
            "WHERE (:sexoParam IS NULL OR :sexoParam = p.sexo)")
    List<Producto> filtroSexo(
            @Param("sexoParam") Producto prod
    );
}
