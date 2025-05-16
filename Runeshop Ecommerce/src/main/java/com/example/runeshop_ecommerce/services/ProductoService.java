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
            ) throws Exception {
        try {
            return productoRepository.filtro(sexo, marca, talleNumero, tipoProducto, nombre, cateoria);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<Producto> filtrarPorPrecio(Double min, Double max) throws Exception {
        try {
            return detalleRepository.filtroPrecio(min, max);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<Producto> ordenarPrecioAscendente() throws Exception {
        try {
            return detalleRepository.ordenarPrecioAsc();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<Producto> ordenarPrecioDescendente() throws Exception {
        try {
            return detalleRepository.ordenarPrecioDesc();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
