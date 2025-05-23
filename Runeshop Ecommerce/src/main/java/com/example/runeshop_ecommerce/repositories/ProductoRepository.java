package com.example.runeshop_ecommerce.repositories;

import com.example.runeshop_ecommerce.entities.Producto;
import com.example.runeshop_ecommerce.entities.enums.Marca;
import com.example.runeshop_ecommerce.entities.enums.TipoProducto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends BaseRepository<Producto, Long> {

    @Query("SELECT DISTINCT p " +
            "FROM Producto p " +
            "JOIN p.detalles d " +
            "JOIN d.talle t " +
            "WHERE (:sexoParam IS NULL OR :sexoParam = p.sexo) " +
            "AND (:marcaParam IS NULL OR :marcaParam = d.marca) " +
            "AND (:talleParam IS NULL OR :talleParam = t.numero) " +
            "AND (:tipoProdParam IS NULL OR :tipoProdParam = p.tipoProducto) " +
            "AND (:nombreParam IS NULL OR :nombreParam = p.modelo) " +
            "AND (:categoriaParam IS NULL OR :categoriaParam = p.categoria.nombre)")
    List<Producto> filtro(
            @Param("sexoParam") String sexo,
            @Param("marcaParam") Marca marca,
            @Param("talleParam") Integer talleNumero,
            @Param("tipoProdParam") TipoProducto tipoProducto,
            @Param("nombreParam") String nombre,
            @Param("categoriaParam") String categoria
    );
}
