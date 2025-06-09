package com.example.runeshop_ecommerce.repositories;

import com.example.runeshop_ecommerce.entities.Producto;
import com.example.runeshop_ecommerce.entities.enums.Marca;
import com.example.runeshop_ecommerce.entities.enums.TipoProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends BaseRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p ORDER BY p.modelo ASC")
    Page<Producto> getProductoPaginado(Pageable pageable);

    @Query("""
    SELECT p
    FROM Producto p
    JOIN p.detalles d
    JOIN d.talle t
    WHERE (:sexoParam IS NULL OR :sexoParam = p.sexo)
    AND (:marcaParam IS NULL OR d.marca IN :marcaParam)
    AND (:talleParam IS NULL OR t.numero IN :talleParam)
    AND (:tipoProdParam IS NULL OR p.tipoProducto IN :tipoProdParam)
    AND (:nombreParam IS NULL OR :nombreParam = p.modelo)
    AND (:categoriaParam IS NULL OR p.categoria.nombre IN :categoriaParam)
    AND (
        (:min IS NULL OR :max IS NULL)
    OR (
        (d.precioDescuento IS NOT NULL AND d.precioDescuento BETWEEN :min AND :max)
        OR (d.precioDescuento IS NULL AND d.precio.precioVenta BETWEEN :min AND :max)
    ))
    ORDER BY 
        CASE 
            WHEN d.precioDescuento IS NOT NULL THEN d.precioDescuento
            ELSE d.precio.precioVenta
    END ASC
    """)
    Page<Producto> filtrarConPaginadoAsc(
            @Param("sexoParam") String sexo,
            @Param("marcaParam") List<Marca> marca,
            @Param("talleParam") List<Integer> talle,
            @Param("tipoProdParam") List<TipoProducto> tipoProducto,
            @Param("nombreParam") String nombre,
            @Param("categoriaParam") List<String> categoria,
            @Param("min") Double min,
            @Param("max") Double max,
            Pageable pageable
    );

    @Query("""
    SELECT p
    FROM Producto p
    JOIN p.detalles d
    JOIN d.talle t
    WHERE (:sexoParam IS NULL OR :sexoParam = p.sexo)
    AND (:marcaParam IS NULL OR d.marca IN :marcaParam)
    AND (:talleParam IS NULL OR t.numero IN :talleParam)
    AND (:tipoProdParam IS NULL OR p.tipoProducto IN :tipoProdParam)
    AND (:nombreParam IS NULL OR :nombreParam = p.modelo)
    AND (:categoriaParam IS NULL OR p.categoria.nombre IN :categoriaParam)
    AND (
        (:min IS NULL OR :max IS NULL)
    OR (
        (d.precioDescuento IS NOT NULL AND d.precioDescuento BETWEEN :min AND :max)
        OR (d.precioDescuento IS NULL AND d.precio.precioVenta BETWEEN :min AND :max)
    ))
    ORDER BY 
        CASE 
            WHEN d.precioDescuento IS NOT NULL THEN d.precioDescuento
            ELSE d.precio.precioVenta
    END DESC 
    """)
    Page<Producto> filtrarConPaginadoDesc(
            @Param("sexoParam") String sexo,
            @Param("marcaParam") List<Marca> marca,
            @Param("talleParam") List<Integer> talle,
            @Param("tipoProdParam") List<TipoProducto> tipoProducto,
            @Param("nombreParam") String nombre,
            @Param("categoriaParam") List<String> categoria,
            @Param("min") Double min,
            @Param("max") Double max,
            Pageable pageable
    );

}
