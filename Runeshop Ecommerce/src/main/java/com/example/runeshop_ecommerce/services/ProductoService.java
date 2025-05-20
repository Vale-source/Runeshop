package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.Producto;
import com.example.runeshop_ecommerce.entities.Talle;
import com.example.runeshop_ecommerce.entities.enums.Marca;
import com.example.runeshop_ecommerce.entities.enums.TipoProducto;
import com.example.runeshop_ecommerce.repositories.DetalleRepository;
import com.example.runeshop_ecommerce.repositories.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoService extends BaseService<Producto, Long> {

    private DetalleRepository detalleRepository;
    private ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository){
        super(productoRepository);
    }

    @Transactional
    public List<Producto> filtroProd (
            String sexo,
            Marca marca,
            Integer talleNumero,
            TipoProducto tipoProducto,
            String nombre,
            String cateoria
            ) {
        return productoRepository.filtro(sexo, marca, talleNumero, tipoProducto, nombre, cateoria);
    }

    @Transactional
    public List<Producto> filtrarPorPrecio(Double min, Double max) throws Exception {
        if (min.isNaN() || max.isNaN()) {
            throw new Exception("Ingrese valores validos para el filtro");
        }
        return detalleRepository.filtroPrecio(min, max);
    }

    @Transactional
    public List<Producto> ordenarPrecioAscendente() {
        return detalleRepository.ordenarPrecioAsc();
    }

    @Transactional
    public List<Producto> ordenarPrecioDescendente() {
            return detalleRepository.ordenarPrecioDesc();
    }
}
